package com.payment.processor;

import org.springframework.stereotype.Component;

@Component
public class PaypalPaymentProcessor implements PaymentProcessor {

    private static final int PRICE_LIMIT = 100000;
    
    @Override
    public boolean processPayment(Integer price) {
        if (price > PRICE_LIMIT) {
            throw new IllegalArgumentException(
                String.format("PayPal payment failed: price cannot exceed %d", PRICE_LIMIT)
            );
        }
        return true;
    }
}