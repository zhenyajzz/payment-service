package com.payment.validation;

import com.payment.model.Country;
import com.payment.model.ProcessorType;
import com.payment.repository.CouponRepository;
import com.payment.request.CalculationRequest;
import com.payment.request.PurchaseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class RequestValidator {

    private final CouponRepository couponRepository;

    public void validateRequest(CalculationRequest request) {
        validateProductId(request.getProduct());
        validateTaxNumber(request.getTaxNumber());
        validateCouponCode(request.getCouponCode());
    }

    public void validatePaymentRequest(PurchaseRequest request) {
        validateProductId(request.getProduct());
        validateTaxNumber(request.getTaxNumber());
        validateCouponCode(request.getCouponCode());
        validatePaymentProcessor(request.getPaymentProcessor());
    }

    private void validateProductId(long productId) {
        if (productId <= 0) {
            throw new IllegalArgumentException("Invalid product id");
        }
    }

    public void validateTaxNumber(String taxNumber) {
        if (isNull(taxNumber) || taxNumber.length() < 2) {
            throw new IllegalArgumentException("Tax number is required");
        }

        String countryCode = taxNumber.substring(0, 2);
        Country country = Country.fromCode(countryCode)
                .orElseThrow(() -> new IllegalArgumentException(
                        "Unsupported country code. Supported: " + Country.getSupportedCodes()
                ));

        String numberPart = taxNumber.substring(2);
        validateNumberFormat(country, numberPart);
    }

    private void validateNumberFormat(Country country, String numberPart) {
        ValidationRule rule = switch (country) {
            case GERMANY, GREECE -> new ValidationRule("\\d{9}", "9 digits", "123456789");
            case ITALY -> new ValidationRule("\\d{11}", "11 digits", "12345678901");
            case FRANCE -> new ValidationRule("[A-Z]{2}\\d{9}", "FR followed by 2 letters and 9 digits", "FRAB123456789");
        };

        if (!numberPart.matches(rule.pattern())) {
            throw new IllegalArgumentException(
                    String.format("Tax number for %s must be: %s (e.g. %s)", country.getCode(), rule.description(), rule.example())
            );
        }
    }

    private void validateCouponCode(String couponCode) {
        if (nonNull(couponCode) && !couponCode.isEmpty()) {
            couponRepository.findByCodeAndActiveTrue(couponCode)
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Coupon code does not exist: '" + couponCode + "'"));
        }
    }

    private void validatePaymentProcessor(String paymentProcessor) {
        try {
            ProcessorType.fromPaymentName(paymentProcessor);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Unsupported payment processor: " + paymentProcessor);
        }
    }

    private record ValidationRule(String pattern, String description, String example) {}
}