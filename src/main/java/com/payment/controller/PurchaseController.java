package com.payment.controller;

import com.payment.request.PurchaseRequest;
import com.payment.response.PurchaseResponse;
import com.payment.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<Map<String, Integer>> purchase(@RequestBody @Valid PurchaseRequest purchaseRequest) {
        purchaseService.purchaseProduct(purchaseRequest);
        return ResponseEntity.ok(Collections.singletonMap("status", 200));
    }

    @PostMapping
    public ResponseEntity<PurchaseResponse> purchaseDetails(@RequestBody @Valid PurchaseRequest purchaseRequest) {
        log.info("Received purchase request: {}", purchaseRequest);
        PurchaseResponse response = purchaseService.purchaseProduct(purchaseRequest);
        log.info("Purchase completed: {}", response);
        return ResponseEntity.ok(response);
    }
    //*
    // Развернутый ответ для метода purchaseDetails
    //    "couponCode": "P10",
    //    "paymentProcessor": "stripe",
    //    "paymentSuccess": true,
    //    "productName": "Iphone",
    //    "taxNumber": "DE123456789",
    //    "totalPrice": 107.1
    // */
}
