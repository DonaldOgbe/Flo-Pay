package com.deodev.PaymentServiceProvider.controller;

import com.deodev.PaymentServiceProvider.dto.UserLoginDTO;
import com.deodev.PaymentServiceProvider.dto.UserRegistrationDTO;
import com.deodev.PaymentServiceProvider.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/auth/register")
    public ResponseEntity<?> signup(@Valid @RequestBody UserRegistrationDTO dto) {
        return  userService.register(dto);
    }

    @GetMapping("/auth/login")
    public ResponseEntity<?> login(@Valid @RequestBody UserLoginDTO dto) {
        return userService.login(dto);
    }

    @GetMapping("/confirm")
    public ResponseEntity<?> verifyEmail(@RequestParam String token) {
        return userService.verify(token);
    }

}
