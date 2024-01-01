package org.abondar.industrial.formatter.type;

import org.abondar.industrial.formatter.Formatter;
import org.abondar.industrial.formatter.validation.ValidationUtil;
import org.abondar.industrial.formatter.exception.FormatException;

import java.util.Locale;

public class BankAccountFormatter extends BaseTextFormatter implements Formatter<String> {

    private static final String IBAN_REGEX = "^([A-Z]{2}[ \\-]?[0-9]{2})(?=(?:[ \\-]?[A-Z0-9]){9,30}$)((?:[ \\-]?[A-Z0-9]{3,5}){2,7})([ \\-]?[A-Z0-9]{1,3})?$";


    @Override
    public String parse(String text, Locale locale) {
        return text;
    }

    @Override
    public String format(String object, String formatExp, Locale locale){
        return super.format(object, formatExp);
    }

    @Override
    public String format(String object, Locale locale){
        return super.format(object);
    }

    @Override
    public boolean isValid(String text) {
       return ValidationUtil.validateString(text,IBAN_REGEX);
    }

}
