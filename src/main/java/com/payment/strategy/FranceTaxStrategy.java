package com.payment.strategy;

import java.math.BigDecimal;

public class FranceTaxStrategy implements TaxStrategy {

    //Можно хранить информацию констант в бд.
    private static final BigDecimal TAX_RATE = new BigDecimal("0.20");
    private static final String COUNTRY_CODE = "FR";

    @Override
    public BigDecimal calculateTax(BigDecimal amount) {
        return amount.multiply(TAX_RATE);
    }

    @Override
    public String getCountryCode() {
        return COUNTRY_CODE;
    }
}