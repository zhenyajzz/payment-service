package com.payment.processor;

import com.payment.model.ProcessorType;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessorFactory {

    public PaymentProcessor createProcessor(ProcessorType type) {
        return switch (type) {
            case PAYPAL -> new PaypalPaymentProcessor();
            case STRIPE -> new StripePaymentProcessor();
        };
    }
}