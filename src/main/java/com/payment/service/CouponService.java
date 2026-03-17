package com.payment.service;

import com.payment.mapper.CouponMapper;
import com.payment.model.Coupon;
import com.payment.repository.CouponRepository;
import com.payment.request.CouponRequest;
import com.payment.response.CouponResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponRepository couponRepository;

    public CouponResponse createCoupon(CouponRequest request) {

        if (couponRepository.existsByCode(request.getCode())) {
            throw new IllegalArgumentException(
                    "Coupon with this code " + request.getCode() + " already exist"
            );
        }
        Coupon coupon = new Coupon();
        coupon.setCode(request.getCode());
        coupon.setDiscountPercent(request.getDiscountPercent());
        coupon.setActive(request.isActive());

        couponRepository.save(coupon);
        return CouponMapper.map(coupon);
    }

    public CouponResponse updateCoupon(long id, CouponRequest request) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("Coupon not found with id: " + id));

        coupon.setCode(request.getCode());
        coupon.setDiscountPercent(request.getDiscountPercent());
        coupon.setActive(request.isActive());

        couponRepository.save(coupon);
        return CouponMapper.map(coupon);
    }

    public List<Coupon> findAllCoupons() {
        return couponRepository.findAll();
    }

    public void deleteCoupon(long id) {
        couponRepository.deleteById(id);
    }
}
