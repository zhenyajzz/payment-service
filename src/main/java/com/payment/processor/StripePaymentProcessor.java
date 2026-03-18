package com.payment.processor;

import org.springframework.stereotype.Component;

@Component
public class StripePaymentProcessor implements PaymentProcessor {
    
    private static final float MIN_PRICE = 100.0f;
    
    @Override
    public boolean processPayment(Integer price) {
        float priceAsFloat = price.floatValue();

        if (priceAsFloat < MIN_PRICE) {
            throw new IllegalArgumentException(String.format("Stripe payment failed: price cannot be lower %d", (int)MIN_PRICE));
        }
        return true;
    }
}