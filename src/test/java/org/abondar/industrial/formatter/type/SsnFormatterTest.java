package org.abondar.industrial.formatter.type;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SsnFormatterTest {

    private SsnFormatter formatter = new SsnFormatter();

    @ParameterizedTest
    @MethodSource("getSsn")
    void validationTest(String ssn){
        assertTrue(formatter.isValid(ssn));
    }

    @Test
    void validateWrongSsnTest(){
        assertFalse(formatter.isValid("123-S5-6789"));
    }

    private static Stream<Arguments> getSsn() {

        return Stream.of(
                Arguments.of("123-45-6789"),
                Arguments.of("000-12-3456"),
                Arguments.of("987-65-4321"),
                Arguments.of("666-12-3456"),
                Arguments.of("123-00-6789"),
                Arguments.of("123-45-0000")
        );
    }
}
