package com.example.smartcity.modules.billing.controller;

import com.example.smartcity.modules.billing.dto.CreateInvoiceRequest;
import com.example.smartcity.modules.billing.dto.InvoiceResponse;
import com.example.smartcity.modules.billing.service.InvoiceService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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

    @PostMapping
    public ResponseEntity<InvoiceResponse> create(@Valid @RequestBody CreateInvoiceRequest request,
                                                  Authentication auth) {
        Long userId = Long.parseLong(auth.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(invoiceService.create(userId, request));
    }
}