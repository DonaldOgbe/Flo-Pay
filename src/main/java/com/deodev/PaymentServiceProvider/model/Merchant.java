package com.deodev.PaymentServiceProvider.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "merchants")
@Data
public class Merchant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "api_key", nullable = false, unique = true)
    private String apiKey;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
