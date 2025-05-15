package com.deodev.PaymentServiceProvider.service;


import com.deodev.PaymentServiceProvider.dto.UserLoginDTO;
import com.deodev.PaymentServiceProvider.dto.UserRegistrationDTO;
import com.deodev.PaymentServiceProvider.exception.UserAlreadyExistsException;
import com.deodev.PaymentServiceProvider.exception.UserNotFoundException;
import com.deodev.PaymentServiceProvider.model.User;
import com.deodev.PaymentServiceProvider.model.VerificationToken;
import com.deodev.PaymentServiceProvider.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VerificationTokenService verificationTokenService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public ResponseEntity<?> register(UserRegistrationDTO dto) {
        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new UserAlreadyExistsException("Email already exists");
        }

        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new UserAlreadyExistsException("Username already exists");
        }

        User user = new ObjectMapper().convertValue(dto, User.class);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User savedUser = userRepository.save(user);
        String token = verificationTokenService.createToken(savedUser);

        emailService.sendEmail(user.getEmail(), token);

        return ResponseEntity.ok("User register successfully");
    }

    public ResponseEntity<?> login(UserLoginDTO dto) {
        User user;

        if (userRepository.existsByUsername(dto.getUserLoginId())) {
            user = userRepository.findByUsername(dto.getUserLoginId());
        } else if (userRepository.existsByEmail(dto.getUserLoginId())) {
            user = userRepository.findByEmail(dto.getUserLoginId());
        } else {
            throw  new UserNotFoundException("Please check username/email or password");
        }

        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw  new UserNotFoundException("Please check username/email or password");
        }

        return ResponseEntity.ok("Login Successful");
    }

    public ResponseEntity<?> verify(String token) {
        VerificationToken verificationToken = verificationTokenService.findById(token)
                .orElseThrow(() -> new EntityNotFoundException("Token not found"));

        User user = verificationToken.getUser();
        user.setVerified(true);
        userRepository.save(user);
        verificationTokenService.delete(verificationToken);

        return ResponseEntity.ok("User verified successfully");
    }
}
