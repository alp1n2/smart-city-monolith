package com.example.billing_service.repository;

import com.example.billing_service.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByUserId(Long userId);
    List<Invoice> findByUserIdAndStatus(Long userId, String status);
}