package com.payment.controller;

import com.payment.model.Coupon;
import com.payment.request.CouponRequest;
import com.payment.response.CouponResponse;
import com.payment.service.CouponService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/coupon")
public class CouponController {

    private final CouponService couponService;

    @GetMapping
    public ResponseEntity<List<Coupon>> findAll() {
        log.info("Fetching all coupons");
        return ResponseEntity.ok(couponService.findAllCoupons());
    }

    @PostMapping
    public ResponseEntity<CouponResponse> create(@RequestBody @Valid CouponRequest request) {
        log.info("Creating new coupon: {}", request.getCode());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(couponService.createCoupon(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CouponResponse> update(@PathVariable long id, @RequestBody @Valid CouponRequest coupon) {
        log.info("Updating coupon with id: {}", id);
        return ResponseEntity.ok(couponService.updateCoupon(id, coupon));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        log.info("Deleting coupon with id: {}", id);
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }
}


