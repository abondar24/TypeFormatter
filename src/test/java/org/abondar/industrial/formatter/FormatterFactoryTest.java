package org.abondar.industrial.formatter;

import org.abondar.industrial.formatter.exception.FormatterFactoryException;
import org.abondar.industrial.formatter.type.EmailFormatter;
import org.abondar.industrial.formatter.type.Type;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

public class FormatterFactoryTest {

    @Test
    void getInstanceTest(){
        var formatterInstance = FormatterFactory.getInstance();
        var formatterInstance1 = FormatterFactory.getInstance();

        assertEquals(formatterInstance,formatterInstance1);
    }

    @Test
    void getFormatterTest(){
        var formatterInstance = FormatterFactory.getInstance();
        var formatter = formatterInstance.getFormatter(Type.EMAIL.name());
        assertTrue(formatter instanceof EmailFormatter);
    }

    @Test
    void getFormatterNotFoundTest(){
        var formatterInstance = FormatterFactory.getInstance();
        assertThrows(FormatterFactoryException.class,()-> formatterInstance.getFormatter("testFormatter"));

    }

    @Test
    void registerFormatterTest(){
        var formatterInstance = FormatterFactory.getInstance();
        var formatter = new TestFormatter();

        formatterInstance.registerFormatter("test",formatter);
        var frm = formatterInstance.getFormatter("test");
        assertEquals(formatter,frm);
    }

    private class TestFormatter implements Formatter<String> {
        @Override
        public String parse(String text, Locale locale) {
            return null;
        }

        @Override
        public String format(String object, Locale locale) {
            return null;
        }

        @Override
        public String format(String object, String formatExp, Locale locale) {
            return null;
        }

        @Override
        public boolean isValid(String text) {
            return false;
        }
    }

}
