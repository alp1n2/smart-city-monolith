package com.example.smartcity.modules.billing.repository;

import com.example.smartcity.modules.billing.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
    List<Invoice> findByUserId(Long userId);
    List<Invoice> findByUserIdAndStatus(Long userId, String status);
}