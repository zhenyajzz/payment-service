package com.payment.response;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Builder
@Getter
@ToString
public class CouponResponse {

    private String code;

    private BigDecimal discountPercent;

    private boolean active;
}
