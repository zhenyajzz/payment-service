package com.payment.controller;

import com.payment.request.PurchaseRequest;
import com.payment.response.PurchaseResponse;
import com.payment.service.PurchaseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private final PurchaseService purchaseService;

    @PostMapping
    public ResponseEntity<PurchaseResponse> create(@RequestBody @Valid PurchaseRequest purchaseRequest) {
        return ResponseEntity.ok(purchaseService.purchaseProduct(purchaseRequest));
    }
}
