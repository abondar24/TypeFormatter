package org.abondar.industrial.formatter.type;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PhoneNumberFormatterTest {

    private PhoneNumberFormatter formatter = new PhoneNumberFormatter();

    @ParameterizedTest
    @MethodSource("getPhoneNumbers")
    void validationTest(String phoneNumber){
        assertTrue(formatter.isValid(phoneNumber));
    }

    @ParameterizedTest
    @MethodSource("getWrongPhoneNumbers")
    void validationWrongTest(String phoneNumber){
        assertFalse(formatter.isValid(phoneNumber));
    }

    private static Stream<Arguments> getPhoneNumbers() {
        return Stream.of(
                Arguments.of("+1-555-123-4567"),
                Arguments.of( "555-123-4567"),
                Arguments.of("(555) 123-4567"),
                Arguments.of("+7 (555) 123 45 67"),
                Arguments.of("555.123.4567"),
                Arguments.of("555●123●4567"),
                Arguments.of("+123456789012"),
                Arguments.of("1234567890"),
                Arguments.of("123-456-7890"),
                Arguments.of("123.456.7890"),
                Arguments.of("123●456●7890")
                );
    }

    private static Stream<Arguments> getWrongPhoneNumbers() {
        return Stream.of(
                Arguments.of( "+12-34-5678901"),
                Arguments.of("+1-555-123-t567"),
                Arguments.of("+1-555-123-45%7")
        );
    }
}
