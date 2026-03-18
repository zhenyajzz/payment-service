package com.payment.strategy;

import java.math.BigDecimal;

public class GreeceTaxStrategy implements TaxStrategy {

    private static final BigDecimal TAX_RATE = new BigDecimal("0.24");
    private static final String COUNTRY_CODE = "GR";

    @Override
    public BigDecimal calculateTax(BigDecimal amount) {
        return amount.multiply(TAX_RATE);
    }

    @Override
    public String getCountryCode() {
        return COUNTRY_CODE;
    }
}