package org.abondar.industrial.formatter.type;

import org.abondar.industrial.formatter.exception.FormatException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Locale;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountFomratterTest {

    private BankAccountFormatter formatter = new BankAccountFormatter();

    @ParameterizedTest
    @MethodSource("getIbans")
    void validationTest(String iban){
        assertTrue(formatter.isValid(iban));
    }

    @Test
    void validateWrongIbanTest(){
        assertFalse(formatter.isValid("29 NWBK 6016 1331 9268 19"));
    }

    @Test
    void parseTest(){
        var iban = "GB29 NWBK 6016 1331 9268 19";
        var res = formatter.parse(iban, Locale.CHINA);
        assertEquals(iban,res);
    }

    @Test
    void defaultFormatTest(){
        var iban = "DE89 3704 0044 0532 0130 00";
        var ibanFormatted = "DE89370400440532013000";

        var res = formatter.format(iban,Locale.CANADA);
        assertEquals(ibanFormatted,res);
    }


    @Test
    void customFormatTest(){
        var iban = "DE89 3704 0044 0532 0130 00";
        var ibanFormatted = "DE89 37";

        var res = formatter.format(iban,"^(.{1,7})",Locale.CANADA);
        assertEquals(ibanFormatted,res);
    }

    @Test
    void formatExceptionTest(){
        assertThrows(FormatException.class, ()->formatter.format(null,Locale.CHINA));
    }

    private static Stream<Arguments> getIbans(){
        return Stream.of(
                Arguments.of( "GB29 NWBK 6016 1331 9268 19"),
                Arguments.of("DE89 3704 0044 0532 0130 00"),
                Arguments.of("FR76 3000 6000 0112 3456 7890 189"),
                Arguments.of("ES91 2100 0418 4502 0005 1332"),
                Arguments.of("IT60 X054 2811 1010 0000 0123 456"),
                Arguments.of("NL91 ABNA 0417 1643 00"),
                Arguments.of("BE68 5390 0754 7034")
        );
    }


}
