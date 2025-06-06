package com.deodev.PaymentServiceProvider.repository;

import com.deodev.PaymentServiceProvider.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User findByEmail(String email);
    User findByUsername(String username);
}
