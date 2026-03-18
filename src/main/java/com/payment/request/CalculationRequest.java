package com.payment.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class CalculationRequest {

    @NotNull(message = "The product must be indicated.")
    private long product;

    @NotNull(message = "The tax number format must be indicated.")
    private String taxNumber;

    private String couponCode;
}
