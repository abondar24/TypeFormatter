package org.abondar.industrial.formatter.type;

import org.abondar.industrial.formatter.exception.FormatException;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class IntegerFormatterTest {

    private IntegerFormatter formatter = new IntegerFormatter();

    @Test
    void validateTest(){
        assertTrue(formatter.isValid("1"));
    }

    @Test
    void validateWrongTest(){
        assertFalse(formatter.isValid("1s"));
    }

    @Test
    void parseTest(){
        var num="1";
        var res = formatter.parse(num,Locale.US);
        assertEquals(1,res);
    }


    @Test
    void formatDefaultTest(){
        var res = formatter.format(1,Locale.US);
        assertEquals("1",res);
    }

    @Test
    void formatCustomTest(){
        var res = formatter.format(1,"0.###E0",Locale.US);
        assertEquals("1E0",res);
    }
}
