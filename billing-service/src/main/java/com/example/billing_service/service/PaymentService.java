package com.example.billing_service.service;

import com.example.billing_service.client.NotificationClient;
import com.example.billing_service.dto.CreatePaymentRequest;
import com.example.billing_service.dto.PaymentResponse;
import com.example.billing_service.entity.Invoice;
import com.example.billing_service.entity.Payment;
import com.example.billing_service.repository.InvoiceRepository;
import com.example.billing_service.repository.PaymentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;
    private final NotificationClient notificationClient;

    public PaymentService(PaymentRepository paymentRepository,
                          InvoiceRepository invoiceRepository,
                          NotificationClient notificationClient) {
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
        this.notificationClient = notificationClient;
    }

    @Transactional
    public PaymentResponse createPayment(Long userId, CreatePaymentRequest request) {
        Invoice invoice = invoiceRepository.findById(request.getInvoiceId())
                .orElseThrow(() -> new IllegalArgumentException("Счёт не найден"));

        if (!invoice.getUserId().equals(userId)) {
            throw new IllegalArgumentException("Счёт принадлежит другому пользователю");
        }

        if ("PAID".equals(invoice.getStatus())) {
            throw new IllegalArgumentException("Счёт уже оплачен");
        }

        Payment payment = new Payment();
        payment.setInvoiceId(invoice.getId());
        payment.setUserId(userId);
        payment.setAmount(request.getAmount());
        payment.setMethod(request.getMethod());
        payment.setStatus("PENDING");
        payment.setTransactionId(UUID.randomUUID().toString());

        return PaymentResponse.from(paymentRepository.save(payment));
    }

    @Transactional
    public PaymentResponse processSuccessWebhook(String transactionId) {
        Payment payment = paymentRepository.findAll().stream()
                .filter(p -> transactionId.equals(p.getTransactionId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Платёж не найден"));

        payment.setStatus("SUCCESS");
        payment.setCompletedAt(LocalDateTime.now());
        paymentRepository.save(payment);

        Invoice invoice = invoiceRepository.findById(payment.getInvoiceId())
                .orElseThrow(() -> new IllegalArgumentException("Счёт не найден"));
        invoice.setStatus("PAID");
        invoiceRepository.save(invoice);

        try {
            Map<String, Object> notification = new HashMap<>();
            notification.put("userId", payment.getUserId());
            notification.put("channel", "EMAIL");
            notification.put("subject", "Платёж прошёл");
            notification.put("message", "Счёт №" + invoice.getId() + " на сумму "
                    + invoice.getAmount() + " " + invoice.getCurrency() + " оплачен.");
            notification.put("sourceService", "BILLING");

            notificationClient.sendNotification(notification);
            log.info("Уведомление отправлено для платежа {}", payment.getId());
        } catch (Exception ex) {
            log.warn("Не удалось отправить уведомление: {}", ex.getMessage());
        }

        return PaymentResponse.from(payment);
    }
}