package com.payment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@Getter
@Builder
@ToString
public class CalculationResponse {

    private String productName;

    private double totalPrice;

    private String couponCode;

    private String country;
}