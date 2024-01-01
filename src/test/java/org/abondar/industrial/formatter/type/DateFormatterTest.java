package org.abondar.industrial.formatter.type;

import org.abondar.industrial.formatter.exception.FormatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class DateFormatterTest {

    private final DateFormatter formatter = new DateFormatter();

    private static Stream<Arguments> getDates() {
        return Stream.of(
                Arguments.of("12/25/2023"),
                Arguments.of("2023/12/12"),
                Arguments.of("01-15-2022"),
                Arguments.of("2023-07-10"),
                Arguments.of("02/29/2020"),
                Arguments.of("11-11-11"),
                Arguments.of("21-01-12"),
                Arguments.of("1999-12-31"),
                Arguments.of("31-08-2000"),
                Arguments.of("25/12/2024")
        );
    }

    private static Stream<Arguments> getWrongDates() {
        return Stream.of(
                Arguments.of("32/01/2023"),
                Arguments.of("02/30/2021"),
                Arguments.of("2023/07/101"),
                Arguments.of("00/01/2023"),
                Arguments.of("01-01-2023-"),
                Arguments.of("31-04-2023"),
                Arguments.of("12/sad/2013")
        );
    }



    @ParameterizedTest
    @MethodSource("getDates")
    void validationTest(String date) {
        assertTrue(formatter.isValid(date));
    }

    @ParameterizedTest
    @MethodSource("getWrongDates")
    void validationWrongTest(String date) {
        assertFalse(formatter.isValid(date));
    }

    @Test
    void parseTest() {
        var res = formatter.parse("2023-12-23", Locale.US);
        assertEquals("Sat Dec 23 00:00:00 CET 2023", res.toString());
    }

    @Test
    void parseWrongTest() {
        assertThrows(FormatException.class, () -> formatter.parse("2023/12/25", Locale.US));
    }

    @Test
    void formatDefaultTest() throws Exception {
        var dateString = "12/25/2023";
        var dateFormat = new SimpleDateFormat("MM/dd/yyyy");
        Date date = dateFormat.parse(dateString);

        var res = formatter.format(date, Locale.US);
        assertEquals("2023-12-25", res);
    }

    @Test
    void formatCustomTest() throws Exception {
        var dateString = "1222-11-20";
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);

        var res = formatter.format(date, "yyyy/MM/dd", Locale.US);
        assertEquals("1222/11/20", res);
    }

    @Test
    void formatWrongTest() throws Exception{
        var dateString = "1222-11-20";
        var dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = dateFormat.parse(dateString);

        assertThrows(FormatException.class,()-> formatter.format(date,"test",Locale.CANADA));
    }
}
