package com.payment.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public enum Country {

    GERMANY("DE"),
    ITALY("IT"),
    FRANCE("FR"),
    GREECE("GR");

    private final String code;

    public static Optional<Country> fromCode(String code) {
        return Arrays.stream(values())
                .filter(country -> country.getCode().equals(code))
                .findFirst();
    }

    public static String getSupportedCodes() {
        return Arrays.stream(values())
                .map(Country::getCode)
                .collect(Collectors.joining(", "));
    }

    public static Country fromTaxNumber(String taxNumber) {
        String countryCode = taxNumber.substring(0, 2);
        for (Country country : values()) {
            if (country.getCode().equals(countryCode)) {
                return country;
            }
        }
        return null;
    }
}
