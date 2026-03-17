package com.payment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Builder
@Getter
@ToString
public class PurchaseResponse {

    private String productName;

    private String taxNumber;

    private String couponCode;

    private double totalPrice;

    private String paymentProcessor;

    private boolean paymentSuccess;
}
