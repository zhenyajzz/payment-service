package com.payment.strategy;

import java.math.BigDecimal;

public class ItalyTaxStrategy implements TaxStrategy {

    private static final BigDecimal TAX_RATE = new BigDecimal("0.22");
    private static final String COUNTRY_CODE = "IT";

    @Override
    public BigDecimal calculateTax(BigDecimal amount) {
        return amount.multiply(TAX_RATE);
    }

    @Override
    public String getCountryCode() {
        return COUNTRY_CODE;
    }
}
