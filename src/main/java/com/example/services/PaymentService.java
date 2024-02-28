package com.example.services;

import com.example.models.Payment;
import com.example.repositoryes.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public void processPayment(Long productId, int quantity) {
        // Создание записи об оплате в базе данных
        Payment payment = new Payment();
        payment.setProductId(productId);
        payment.setQuantity(quantity);
        payment.setAmount(10 * quantity);
        paymentRepository.save(payment);
    }
}
