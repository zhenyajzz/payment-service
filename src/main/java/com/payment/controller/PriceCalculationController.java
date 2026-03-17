package com.payment.controller;

import com.payment.request.CalculationRequest;
import com.payment.response.CalculationResponse;
import com.payment.service.PriceCalculationService;
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
public class PriceCalculationController {

    private final PriceCalculationService calculationService;

    @PostMapping
    public ResponseEntity<?> calculatePrice(@RequestBody @Valid CalculationRequest request) {
        log.info("Received price calculation request: {}", request);
        CalculationResponse response = calculationService.calculatePrice(request);
        log.info("Price calculated successfully: {}", response);
        return ResponseEntity.ok(response);
    }
}