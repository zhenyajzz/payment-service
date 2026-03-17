package com.payment.helper;

import com.payment.model.Coupon;
import com.payment.model.DiscountType;
import com.payment.model.Product;
import com.payment.strategy.TaxStrategy;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Component
public class PriceCalculationHelper {

    //Процент от суммы покупки, если сумма привышает 500(Сделано для примера ситуации, когда сумма покупки высокая и за это клиент получает скидку как пример 5 процентов)
    private static final BigDecimal BULK_DISCOUNT_THRESHOLD = new BigDecimal("500");
    private static final BigDecimal BULK_DISCOUNT_RATE = new BigDecimal("5");

    public double calculatePrice(Product product, TaxStrategy taxStrategy, Coupon coupon) {
        BigDecimal originalPrice = product.getPrice();

        DiscountType appliedDiscount = determineDiscountType(coupon, originalPrice);
        BigDecimal priceAfterDiscount = applySelectedDiscount(originalPrice, coupon, appliedDiscount);

        BigDecimal taxAmount = taxStrategy.calculateTax(priceAfterDiscount);
        BigDecimal totalPrice = priceAfterDiscount.add(taxAmount);

        return totalPrice.doubleValue();
    }

    private DiscountType determineDiscountType(Coupon coupon, BigDecimal amount) {
        if (nonNull(coupon)) {
            return DiscountType.COUPON;
        }
        if (amount.compareTo(BULK_DISCOUNT_THRESHOLD) > 0) {
            return DiscountType.BULK;
        }
        return DiscountType.NONE;
    }

    private BigDecimal applySelectedDiscount(BigDecimal amount, Coupon coupon, DiscountType discountType) {
        return switch (discountType) {
            case COUPON -> {
                BigDecimal couponDiscount = calculateCouponDiscount(coupon, amount);
                yield amount.subtract(couponDiscount);
            }
            case BULK -> {
                BigDecimal bulkDiscount = amount.multiply(
                        BULK_DISCOUNT_RATE.divide(new BigDecimal("100"))
                );
                yield amount.subtract(bulkDiscount);
            }
            default -> amount;
        };
    }

    private BigDecimal calculateCouponDiscount(Coupon coupon, BigDecimal amount) {
        return amount.multiply(coupon.getDiscountPercent().divide(new BigDecimal("100")));
    }
}
