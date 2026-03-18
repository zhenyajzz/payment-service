package com.payment.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class CouponRequest {

    @NotNull(message = "Must not be null.")
    private String code;

    @Min(value = 0, message = "Must not be less than 0.")
    @Max(value = 100, message = "Must not be greater than 100.")
    @NotNull(message = "Must not be null.")
    private BigDecimal discountPercent;

    private boolean active;
}
