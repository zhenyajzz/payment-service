package com.payment.validator;

import com.payment.repository.CouponRepository;
import com.payment.validation.RequestValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class TaxNumberValidationTest {

    private RequestValidator validator;

    @BeforeEach
    void setUp() {
        validator = new RequestValidator(mock(CouponRepository.class));
    }

    @Test
    void testValidTaxNumbers() {
        List<String> validNumbers = Arrays.asList(
                "DE123456789",
                "IT12345678901",
                "GR123456789",
                "FRAA123456789"
        );

        for (String number : validNumbers) {
            assertDoesNotThrow(() -> validator.validateTaxNumber(number));
        }
    }

    @Test
    void testInvalidTaxNumbers() {
        List<String> invalidNumbers = Arrays.asList(
                "DE12345678",  // слишком короткий
                "IT12345678",  // неправильный формат
                "FR123456789", // неправильный формат (нужны буквы)
                "UK123456789", // неподдерживаемая страна
                "DE12A456789", // содержит буквы
                "",            // пустой
                "   ",         // пробелы
                null           // null
        );

        for (String number : invalidNumbers) {
            assertThrows(IllegalArgumentException.class,
                    () -> validator.validateTaxNumber(number),
                    "Should throw exception for: " + number
            );
        }
    }
}