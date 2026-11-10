package com.example.smartcity.modules.billing.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateInvoiceRequest {
    private String serviceType;
    private BigDecimal amount;
    private String description;

    // Геттеры и сеттеры
    public String getServiceType() { return serviceType; }
    public void setServiceType(String serviceType) { this.serviceType = serviceType; }
    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}