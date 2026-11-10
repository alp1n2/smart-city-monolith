package com.example.smartcity.modules.billing.service;

import com.example.smartcity.modules.billing.dto.CreatePaymentRequest;
import com.example.smartcity.modules.billing.dto.PaymentResponse;
import com.example.smartcity.modules.billing.entity.Invoice;
import com.example.smartcity.modules.billing.entity.Payment;
import com.example.smartcity.modules.billing.repository.InvoiceRepository;
import com.example.smartcity.modules.billing.repository.PaymentRepository;
import com.example.smartcity.modules.notification.api.NotificationApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class PaymentService {

    private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;
    private final NotificationApi notificationApi; // Внедряем интерфейс

    public PaymentService(PaymentRepository paymentRepository, InvoiceRepository invoiceRepository, NotificationApi notificationApi) {
        this.paymentRepository = paymentRepository;
        this.invoiceRepository = invoiceRepository;
        this.notificationApi = notificationApi;
    }

    @Transactional
    public PaymentResponse createPayment(Long userId, CreatePaymentRequest request) {
        Invoice invoice = invoiceRepository.findById(request.getInvoiceId())
                .orElseThrow(() -> new IllegalArgumentException("Счёт не найден"));

        Payment payment = new Payment();
        payment.setInvoiceId(invoice.getId());
        payment.setUserId(userId);
        payment.setAmount(request.getAmount());
        payment.setMethod(request.getMethod());
        payment.setTransactionId(UUID.randomUUID().toString());
        return PaymentResponse.from(paymentRepository.save(payment));
    }

    @Transactional
    public PaymentResponse processSuccessWebhook(String transactionId) {
        Payment payment = paymentRepository.findByTransactionId(transactionId);
        if (payment == null) throw new IllegalArgumentException("Платёж не найден");

        payment.setStatus("SUCCESS");
        payment.setCompletedAt(LocalDateTime.now());
        paymentRepository.save(payment);

        Invoice invoice = invoiceRepository.findById(payment.getInvoiceId())
                .orElseThrow(() -> new IllegalArgumentException("Счёт не найден"));
        invoice.setStatus("PAID");
        invoiceRepository.save(invoice);

        // --- ПРЯМОЙ ВЫЗОВ ВТОРОГО МОДУЛЯ (БЕЗ HTTP) ---
        try {
            notificationApi.send(
                    payment.getUserId(),
                    "EMAIL",
                    "Платёж прошёл",
                    "Счёт №" + invoice.getId() + " успешно оплачен.",
                    "BILLING"
            );
        } catch (Exception ex) {
            log.warn("Ошибка отправки уведомления: {}", ex.getMessage());
        }

        return PaymentResponse.from(payment);
    }
}