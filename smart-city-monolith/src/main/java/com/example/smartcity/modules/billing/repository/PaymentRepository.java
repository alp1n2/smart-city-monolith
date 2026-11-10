package com.example.smartcity.modules.billing.repository;

import com.example.smartcity.modules.billing.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByTransactionId(String transactionId);
}