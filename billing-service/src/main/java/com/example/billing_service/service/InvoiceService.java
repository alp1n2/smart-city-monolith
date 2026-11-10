package com.example.billing_service.service;

import com.example.billing_service.dto.InvoiceResponse;
import com.example.billing_service.entity.Invoice;
import com.example.billing_service.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<InvoiceResponse> getByUser(Long userId, String status) {
        List<Invoice> invoices;

        if (status != null && !status.isBlank()) {
            invoices = invoiceRepository.findByUserIdAndStatus(userId, status);
        } else {
            invoices = invoiceRepository.findByUserId(userId);
        }

        return invoices.stream().map(InvoiceResponse::from).toList();
    }
}