package com.payment.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class PurchaseRequest {

    @NotNull(message = "The product must be indicated.")
    private long product;

    @NotNull(message = "The tax number format must be indicated.")
    private String taxNumber;

    private String couponCode;

    @NotNull(message = "Payment processor is required.")
    @Pattern(regexp = "^(paypal|stripe)$",
            message = "Payment processor must be either 'paypal' or 'stripe'")
    private String paymentProcessor;
}
