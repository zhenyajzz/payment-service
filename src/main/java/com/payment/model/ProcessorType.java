package com.payment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

import static java.util.Objects.isNull;

@AllArgsConstructor
@Getter
public enum ProcessorType {
    PAYPAL("paypal"),
    STRIPE("stripe");

    private final String paymentName;

    public static ProcessorType fromPaymentName(String paymentName) {
        return Arrays.stream(values())
                .filter(type -> type.paymentName.equalsIgnoreCase(paymentName))
                .findFirst()
                .orElseThrow(() ->
                        new IllegalArgumentException("Unknown payment processor: " + paymentName));
    }
}