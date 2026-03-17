package com.payment.strategy;

import java.math.BigDecimal;

public interface TaxStrategy {
    BigDecimal calculateTax(BigDecimal amount);
    String getCountryCode();
}
