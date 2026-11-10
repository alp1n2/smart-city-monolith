package com.example.billing_service.repository;

import com.example.billing_service.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByInvoiceId(Long invoiceId);
    List<Payment> findByUserId(Long userId);
}