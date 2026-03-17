package com.payment.service;

import com.payment.model.Country;
import com.payment.model.Coupon;
import com.payment.model.Product;
import com.payment.repository.CouponRepository;
import com.payment.repository.ProductRepository;
import com.payment.request.CalculationRequest;
import com.payment.response.CalculationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class PriceCalculationService {

    private final ProductRepository productRepository;
    private final CouponRepository couponRepository;
    private final RequestValidator requestValidator;
    private final PriceCalculationHelper priceCalculationHelper;

    public CalculationResponse calculatePrice(CalculationRequest request) {
        requestValidator.validateRequest(request);

        Product product = productRepository.findById(request.getProduct())
                .orElseThrow(() -> new IllegalArgumentException("Product not found with id: " + request.getProduct()));

        Coupon coupon = couponRepository.findByCode(request.getCouponCode());

        Country country = Country.fromTaxNumber(request.getTaxNumber());
        TaxStrategy taxStrategy = TaxStrategyFactory.getStrategyFromTaxNumber(request.getTaxNumber());

        double totalPrice = priceCalculationHelper.calculatePrice(product, taxStrategy, coupon);

        return CalculationResponse.builder()
                .productName(product.getName())
                .totalPrice(totalPrice)
                .country(country.name())
                .couponCode(nonNull(coupon) ? coupon.getCode() : "Coupon was not applied")
                .build();
    }
}
