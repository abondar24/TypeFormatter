package org.abondar.industrial.formatter.type;

import org.abondar.industrial.formatter.exception.FormatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalTime;
import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class TimeFormatterTest {

    private final TimeFormatter formatter = new TimeFormatter();

    private static Stream<Arguments> getTime() {
        return Stream.of(
                Arguments.of("00:00"),
                Arguments.of("01:30"),
                Arguments.of("12:45"),
                Arguments.of("23:59:59"),
                Arguments.of("900"),
                Arguments.of("1300"),
                Arguments.of("2400"),
                Arguments.of("113456"),
                Arguments.of("9 am"),
                Arguments.of("12:00 pm")
                );
    }

    private static Stream<Arguments> getWrongTime() {
        return Stream.of(
                Arguments.of("12te"),
                Arguments.of("00:AA"),
                Arguments.of("12:34:5678")
        );
    }

    private static Stream<Arguments> getParseTime() {
        return Stream.of(
                Arguments.of("9:00"),
                Arguments.of("900"),
                Arguments.of("09:00"),
                Arguments.of("09:00:00"),
                Arguments.of("9 am"),
                Arguments.of("9:00 am"),
                Arguments.of("09:00 am")
        );
    }

    @ParameterizedTest
    @MethodSource("getTime")
    void validationTest(String time) {
        assertTrue(formatter.isValid(time));
    }

    @ParameterizedTest
    @MethodSource("getWrongTime")
    void validationWrongTest(String date) {
        assertFalse(formatter.isValid(date));
    }

    @ParameterizedTest
    @MethodSource("getParseTime")
    void parseTest(String parseTime) {
        var res = formatter.parse(parseTime, Locale.US);
        assertEquals("09:00", res.toString());
    }

    @Test
    void parseWrongTest() {
        assertThrows(FormatException.class, () -> formatter.parse("1220a", Locale.US));
    }

    @Test
    void formatDefaultTest() {
        LocalTime time = LocalTime.of(13, 45);
        var res = formatter.format(time, Locale.US);
        assertEquals("13:45", res);
    }

    @Test
    void formatCustomTest() {
        LocalTime time = LocalTime.of(13, 45);

        var res = formatter.format(time, "HHmm", Locale.US);
        assertEquals("1345", res);
    }

    @Test
    void formatWrongTest() {
        LocalTime time = LocalTime.of(13, 45);

        assertThrows(FormatException.class, () -> formatter.format(time, "test", Locale.CANADA));
    }
}
