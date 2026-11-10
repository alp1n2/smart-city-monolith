package com.example.billing_service.dto;

import com.example.billing_service.entity.Payment;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponse {
    private Long id;
    private Long invoiceId;
    private Long userId;
    private BigDecimal amount;
    private String method;
    private String status;
    private String transactionId;
    private LocalDateTime createdAt;
    private LocalDateTime completedAt;

    public PaymentResponse(Long id, Long invoiceId, Long userId,
                           BigDecimal amount, String method, String status,
                           String transactionId, LocalDateTime createdAt,
                           LocalDateTime completedAt) {
        this.id = id;
        this.invoiceId = invoiceId;
        this.userId = userId;
        this.amount = amount;
        this.method = method;
        this.status = status;
        this.transactionId = transactionId;
        this.createdAt = createdAt;
        this.completedAt = completedAt;
    }

    public static PaymentResponse from(Payment p) {
        return new PaymentResponse(
                p.getId(), p.getInvoiceId(), p.getUserId(),
                p.getAmount(), p.getMethod(), p.getStatus(),
                p.getTransactionId(), p.getCreatedAt(), p.getCompletedAt()
        );
    }

    public Long getId() { return id; }
    public Long getInvoiceId() { return invoiceId; }
    public Long getUserId() { return userId; }
    public BigDecimal getAmount() { return amount; }
    public String getMethod() { return method; }
    public String getStatus() { return status; }
    public String getTransactionId() { return transactionId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getCompletedAt() { return completedAt; }
}