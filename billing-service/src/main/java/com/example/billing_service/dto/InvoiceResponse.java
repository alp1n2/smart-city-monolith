package com.example.billing_service.dto;

import com.example.billing_service.entity.Invoice;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class InvoiceResponse {
    private Long id;
    private Long userId;
    private String serviceType;
    private BigDecimal amount;
    private String currency;
    private String status;
    private String description;
    private LocalDateTime dueDate;
    private LocalDateTime createdAt;

    public InvoiceResponse(Long id, Long userId, String serviceType,
                           BigDecimal amount, String currency, String status,
                           String description, LocalDateTime dueDate,
                           LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.serviceType = serviceType;
        this.amount = amount;
        this.currency = currency;
        this.status = status;
        this.description = description;
        this.dueDate = dueDate;
        this.createdAt = createdAt;
    }

    public static InvoiceResponse from(Invoice i) {
        return new InvoiceResponse(
                i.getId(), i.getUserId(), i.getServiceType(),
                i.getAmount(), i.getCurrency(), i.getStatus(),
                i.getDescription(), i.getDueDate(), i.getCreatedAt()
        );
    }

    public Long getId() { return id; }
    public Long getUserId() { return userId; }
    public String getServiceType() { return serviceType; }
    public BigDecimal getAmount() { return amount; }
    public String getCurrency() { return currency; }
    public String getStatus() { return status; }
    public String getDescription() { return description; }
    public LocalDateTime getDueDate() { return dueDate; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}