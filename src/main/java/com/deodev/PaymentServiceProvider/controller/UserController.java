package com.deodev.PaymentServiceProvider.controller;

import com.deodev.PaymentServiceProvider.dto.UserRegistrationDTO;
import com.deodev.PaymentServiceProvider.model.User;
import com.deodev.PaymentServiceProvider.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> signup(@Valid @RequestBody UserRegistrationDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            // If validation fails, return all errors in the response
            return ResponseEntity.badRequest().body(result.getAllErrors());
        }
        System.out.println(dto.toString());
        return  userService.register(dto);
    }


}
