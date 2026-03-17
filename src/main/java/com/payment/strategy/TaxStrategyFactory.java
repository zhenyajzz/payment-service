package com.payment.strategy;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TaxStrategyFactory {

    private static final Map<String, TaxStrategy> strategies = new HashMap<>();

    static {
        TaxStrategy germany = new GermanyTaxStrategy();
        TaxStrategy italy = new ItalyTaxStrategy();
        TaxStrategy france = new FranceTaxStrategy();
        TaxStrategy greece = new GreeceTaxStrategy();

        strategies.put(germany.getCountryCode(), germany);
        strategies.put(italy.getCountryCode(), italy);
        strategies.put(france.getCountryCode(), france);
        strategies.put(greece.getCountryCode(), greece);
    }

    public static TaxStrategy getStrategy(String countryCode) {
        return strategies.get(countryCode);
    }

    public static TaxStrategy getStrategyFromTaxNumber(String taxNumber) {
        String countryCode = taxNumber.substring(0, 2);
        return getStrategy(countryCode);
    }
}
