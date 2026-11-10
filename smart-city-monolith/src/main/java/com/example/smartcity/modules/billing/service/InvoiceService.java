package com.example.smartcity.modules.billing.service;

import com.example.smartcity.modules.billing.dto.CreateInvoiceRequest;
import com.example.smartcity.modules.billing.dto.InvoiceResponse;
import com.example.smartcity.modules.billing.entity.Invoice;
import com.example.smartcity.modules.billing.repository.InvoiceRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public InvoiceResponse create(Long userId, CreateInvoiceRequest request) {
        Invoice invoice = new Invoice();
        invoice.setUserId(userId);
        invoice.setServiceType(request.getServiceType());
        invoice.setAmount(request.getAmount());
        invoice.setDescription(request.getDescription());
        invoice.setStatus("PENDING");
        invoice.setDueDate(LocalDateTime.now().plusDays(30));
        invoice.setCurrency("RUB");

        return InvoiceResponse.from(invoiceRepository.save(invoice));
    }
}