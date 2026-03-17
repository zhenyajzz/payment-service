package com.payment.processor;

public interface PaymentProcessor {
    boolean processPayment(Integer price);
}