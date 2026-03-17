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

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/purchase")
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseResponse> create(@RequestBody @Valid PurchaseRequest purchaseRequest) {
        log.info("Received purchase request: {}", purchaseRequest);
        PurchaseResponse response = purchaseService.purchaseProduct(purchaseRequest);
        log.info("Purchase completed: {}", response);
        return ResponseEntity.ok(response);
    }
}
