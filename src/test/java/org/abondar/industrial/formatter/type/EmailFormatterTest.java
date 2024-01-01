package org.abondar.industrial.formatter.type;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmailFormatterTest {

    private EmailFormatter formatter = new EmailFormatter();

    @ParameterizedTest
    @MethodSource("getEmails")
    void validationTest(String email){
        assertTrue(formatter.isValid(email));
    }

    @ParameterizedTest
    @MethodSource("getWrongEmails")
    void validationWrongTest(String email){
        assertFalse(formatter.isValid(email));
    }

    private static Stream<Arguments> getEmails() {
        return Stream.of(
                Arguments.of( "example@example.com"),
                Arguments.of("user123@gmail.com")
        );
    }

    private static Stream<Arguments> getWrongEmails() {
        return Stream.of(
                Arguments.of("invalid-email"),
                Arguments.of("invalid-email"),
                Arguments.of("no@domain"),
                Arguments.of("missingAtSign.com")
        );
    }
}
