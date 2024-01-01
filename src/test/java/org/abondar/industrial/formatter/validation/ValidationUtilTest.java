package org.abondar.industrial.formatter.validation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationUtilTest {


    @Test
    void validateRegex() {
        var regex = "[0-9A-Za-z]+$";
        var text = "test123";
        assertTrue(ValidationUtil.validateString(text, regex));
    }

    @ParameterizedTest
    @MethodSource("validationInput")
    void validateRegexWrongText(String text, String regex) {
        assertFalse(ValidationUtil.validateString(text, regex));
    }

    private static Stream<Arguments> validationInput() {
        return Stream.of(
                Arguments.of("test-123", "[0-9A-Za-z]+$"),
                Arguments.of("", "[0-9A-Za-z]+$"),
                Arguments.of(" ", "[0-9A-Za-z]+$"),
                Arguments.of(null, "[0-9A-Za-z]+$"),
                Arguments.of("test123", null),
                Arguments.of("test123", ""),
                Arguments.of("test123", " ")
                );

    }

}
