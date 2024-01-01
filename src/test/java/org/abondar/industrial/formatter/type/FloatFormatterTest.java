package org.abondar.industrial.formatter.type;

import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FloatFormatterTest {

    private FloatFormatter formatter = new FloatFormatter();

    @Test
    void validateTest(){
        assertTrue(formatter.isValid("1.00"));
    }

    @Test
    void validateWrongTest(){
        assertFalse(formatter.isValid("1.0s"));
    }

    @Test
    void parseTest(){
        var num="1.00";
        var res = formatter.parse(num, Locale.US);
        assertEquals(1.00f,res);
    }


    @Test
    void formatDefaultTest(){
        var res = formatter.format(1.00f,Locale.US);
        assertEquals("1.00",res);
    }

    @Test
    void formatCustomTest(){
        var res = formatter.format(1.00f,"+#0.00;-#0.00",Locale.US);
        assertEquals("+1.00",res);
    }
}
