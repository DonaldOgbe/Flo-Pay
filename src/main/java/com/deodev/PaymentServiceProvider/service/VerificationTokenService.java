package com.deodev.PaymentServiceProvider.service;


import com.deodev.PaymentServiceProvider.model.User;
import com.deodev.PaymentServiceProvider.model.VerificationToken;
import com.deodev.PaymentServiceProvider.repository.VerificationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VerificationTokenService {

    @Autowired
    private VerificationTokenRepository verificationTokenRepository;

    public String createToken(User user) {

        VerificationToken verificationToken = verificationTokenRepository.save(new VerificationToken(user));

        return verificationToken.getToken();
    }

    public Optional<VerificationToken> findById(String token) {
        return verificationTokenRepository.findById(token);
    }

    public void delete(VerificationToken verificationToken) {
        verificationTokenRepository.delete(verificationToken);
    }
}
