package org.abondar.industrial.formatter.type;

import org.abondar.industrial.formatter.Formatter;
import org.abondar.industrial.formatter.validation.ValidationUtil;
import org.abondar.industrial.formatter.exception.FormatException;

import java.util.Locale;

public class EmailFormatter extends BaseTextFormatter implements Formatter<String> {

    private static final String EMAIL_REGEX =
            "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";


    @Override
    public String parse(String text, Locale locale){
        return text;
    }

    @Override
    public String format(String object, String formatExp, Locale locale) {
        return super.format(object,formatExp);
    }

    @Override
    public String format(String object, Locale locale) {
        return super.format(object);
    }

    @Override
    public boolean isValid(String text) {
        return ValidationUtil.validateString(text,EMAIL_REGEX);
    }
}
