package com.payment.service;

import com.payment.helper.PriceCalculationHelper;
import com.payment.model.Coupon;
import com.payment.model.ProcessorType;
import com.payment.model.Product;
import com.payment.repository.CouponRepository;
import com.payment.repository.ProductRepository;
import com.payment.request.PurchaseRequest;
import com.payment.response.PurchaseResponse;
import com.payment.strategy.TaxStrategy;
import com.payment.strategy.TaxStrategyFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class PurchaseService {

    private final ProductRepository productRepository;
    private final RequestValidator requestValidator;
    private final PaymentProcessorFactory processorFactory;
    private final PriceCalculationHelper priceCalculationHelper;
    private final CouponRepository couponRepository;

    public PurchaseResponse purchaseProduct(PurchaseRequest request) {
        requestValidator.validatePaymentRequest(request);

        Product product = productRepository.findById(request.getProduct())
                .orElseThrow(() -> new IllegalArgumentException(
                        "Product not found with id: " + request.getProduct()));

        Coupon coupon = couponRepository.findByCode(request.getCouponCode());

        TaxStrategy taxStrategy = TaxStrategyFactory.getStrategyFromTaxNumber(request.getTaxNumber());
        double totalPrice = priceCalculationHelper.calculatePrice(product, taxStrategy, coupon);

        PaymentProcessor paymentProcessor = processorFactory.createProcessor(ProcessorType.fromPaymentName(request.getPaymentProcessor()));
        boolean processed = paymentProcessor.processPayment((int) totalPrice);

        return PurchaseResponse.builder()
                .productName(product.getName())
                .taxNumber(request.getTaxNumber())
                .paymentProcessor(request.getPaymentProcessor())
                .paymentSuccess(processed)
                .couponCode(nonNull(coupon) ? coupon.getCode() : "Coupon was not applied")
                .totalPrice(totalPrice)
                .build();
    }
}
