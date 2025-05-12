package com.deodev.PaymentServiceProvider.service;


import com.deodev.PaymentServiceProvider.dto.UserLoginDTO;
import com.deodev.PaymentServiceProvider.dto.UserRegistrationDTO;
import com.deodev.PaymentServiceProvider.exception.UserAlreadyExistsException;
import com.deodev.PaymentServiceProvider.exception.UserNotFoundException;
import com.deodev.PaymentServiceProvider.model.User;
import com.deodev.PaymentServiceProvider.repository.UserRepository;
import com.deodev.PaymentServiceProvider.response.GenericApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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

        userRepository.save(user);

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
}
