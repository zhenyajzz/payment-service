package com.payment.controller;

import com.payment.model.Coupon;
import com.payment.request.CouponRequest;
import com.payment.response.CouponResponse;
import com.payment.service.CouponService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Coupon Controller", description = "Endpoints for managing discount coupons")
public class CouponController {

    private final CouponService couponService;

    @GetMapping
    @Operation(
            summary = "Get all coupons",
            description = "Retrieves a list of all available discount coupons"
    )
    public ResponseEntity<List<Coupon>> findAll() {
        log.info("Fetching all coupons");
        return ResponseEntity.ok(couponService.findAllCoupons());
    }

    @PostMapping
    @Operation(
            summary = "Create a new coupon",
            description = "Creates a new discount coupon with the specified details"
    )
    public ResponseEntity<CouponResponse> create(@RequestBody @Valid CouponRequest request) {
        log.info("Creating new coupon: {}", request.getCode());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(couponService.createCoupon(request));
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Update a coupon",
            description = "Updates an existing coupon by id"
    )
    public ResponseEntity<CouponResponse> update(@PathVariable long id, @RequestBody @Valid CouponRequest coupon) {
        log.info("Updating coupon with id: {}", id);
        return ResponseEntity.ok(couponService.updateCoupon(id, coupon));
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Delete a coupon",
            description = "Deletes an existing coupon by id"
    )
    public ResponseEntity<Void> delete(@PathVariable long id) {
        log.info("Deleting coupon with id: {}", id);
        couponService.deleteCoupon(id);
        return ResponseEntity.noContent().build();
    }
}


