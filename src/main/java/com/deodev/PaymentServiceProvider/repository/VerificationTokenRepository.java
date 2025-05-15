package com.deodev.PaymentServiceProvider.repository;

import com.deodev.PaymentServiceProvider.model.VerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, String> {
}
