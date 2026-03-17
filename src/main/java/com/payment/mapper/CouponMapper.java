package com.payment.mapper;

import com.payment.model.Coupon;
import com.payment.response.CouponResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CouponMapper {

    public static CouponResponse map(Coupon coupon) {
        return CouponResponse.builder()
                .code(coupon.getCode())
                .discountPercent(coupon.getDiscountPercent())
                .active(coupon.isActive())
                .build();
    }
}
