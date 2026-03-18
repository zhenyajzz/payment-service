package com.payment.controller;

import com.payment.request.CalculationRequest;
import com.payment.response.CalculationResponse;
import com.payment.service.PriceCalculationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/calculate-price")
@Tag(name = "Price Calculation Controller", description = "Endpoints for calculating product prices with tax and discounts")
public class PriceCalculationController {

    private final PriceCalculationService calculationService;

    @PostMapping
    @Operation(
            summary = "Calculate product price",
            description = "Calculates final product price including tax based on taxNumber and applying discount coupon if provided"
    )
    public ResponseEntity<CalculationResponse> calculatePrice(@RequestBody @Valid CalculationRequest request) {
        log.info("Received price calculation request: {}", request);
        CalculationResponse response = calculationService.calculatePrice(request);
        log.info("Price calculated successfully: {}", response);
        return ResponseEntity.ok(response);
    }
}