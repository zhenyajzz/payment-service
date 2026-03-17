package com.payment.strategy;

import java.math.BigDecimal;

public class GermanyTaxStrategy implements TaxStrategy {

    //Можно хранить информацию констант в бд.
    private static final BigDecimal TAX_RATE = new BigDecimal("0.19");
    private static final String COUNTRY_CODE = "DE";

    @Override
    public BigDecimal calculateTax(BigDecimal amount) {
        return amount.multiply(TAX_RATE);
    }

    @Override
    public String getCountryCode() {
        return COUNTRY_CODE;
    }
}
