package com.deodev.PaymentServiceProvider.service;

import com.deodev.PaymentServiceProvider.exception.EmailSendingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Async
    public void sendEmail(String emailAddress, String token) {
        String subject = "Confirm your registration";
        String confirmationUrl = "http://localhost:8080/api/confirm?token=" + token;
        String message = "Please click the link to confirm your registration:\n" + confirmationUrl;

        SimpleMailMessage email = new SimpleMailMessage();
        email.setTo(emailAddress);
        email.setSubject(subject);
        email.setText(message);

        try {
            mailSender.send(email);
        } catch (EmailSendingException e) {
            throw new RuntimeException("Unable to Send Mail");
        }

    }
}
