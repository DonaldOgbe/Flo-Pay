package com.deodev.PaymentServiceProvider.model;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "merchants")
@Data
public class Merchant {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "api_key", nullable = false, unique = true)
    private String apiKey;

    @Column(nullable = true, unique = true)
    private String supportEmail;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
