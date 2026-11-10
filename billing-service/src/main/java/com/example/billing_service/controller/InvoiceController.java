package com.example.billing_service.controller;

import com.example.billing_service.dto.InvoiceResponse;
import com.example.billing_service.service.InvoiceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class InvoiceController {

    private final InvoiceService invoiceService;


    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/{userId}/invoices")
    public ResponseEntity<List<InvoiceResponse>> getInvoices(
            @PathVariable Long userId,
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok(invoiceService.getByUser(userId, status));
    }
}