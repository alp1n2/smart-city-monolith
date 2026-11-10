package com.example.billing_service.controller;

import com.example.billing_service.dto.CreatePaymentRequest;
import com.example.billing_service.dto.PaymentResponse;
import com.example.billing_service.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;


    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> create(@Valid @RequestBody CreatePaymentRequest request,
                                                  Authentication auth) {
        Long userId = Long.parseLong(auth.getName());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(paymentService.createPayment(userId, request));
    }

    @PostMapping("/webhook/success")
    public ResponseEntity<PaymentResponse> webhookSuccess(@RequestBody Map<String, String> body) {
        String transactionId = body.get("transactionId");
        return ResponseEntity.ok(paymentService.processSuccessWebhook(transactionId));
    }
}