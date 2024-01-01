package org.abondar.industrial.formatter;

import org.abondar.industrial.formatter.exception.FormatException;
import org.abondar.industrial.formatter.validation.ValidationStatus;

import java.util.Locale;

public interface Formatter <T>{
    T parse(String text, Locale locale);
    String format(T object, Locale locale);
    String format(T object,String formatExp, Locale locale);
    boolean isValid(String text);

}
