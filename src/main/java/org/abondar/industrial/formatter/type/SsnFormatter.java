package org.abondar.industrial.formatter.type;

import org.abondar.industrial.formatter.Formatter;
import org.abondar.industrial.formatter.validation.ValidationUtil;
import org.abondar.industrial.formatter.exception.FormatException;

import java.util.Locale;

public class SsnFormatter extends BaseTextFormatter implements Formatter<String> {

    private static final String SSN_REGEX = "^(\\d{3}-?\\d{2}-?\\d{4}|XXX-XX-XXXX)$";


    @Override
    public String parse(String text, Locale locale) {
        return text;
    }

    @Override
    public String format(String object, String formatExp, Locale locale) {
        return super.format(object,formatExp);
    }

    @Override
    public String format(String object, Locale locale)  {
        return super.format(object);
    }

    @Override
    public boolean isValid(String text) {
        return ValidationUtil.validateString(text,SSN_REGEX);
    }
}

