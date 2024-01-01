package org.abondar.industrial.formatter.type;

import org.abondar.industrial.formatter.Formatter;

import java.util.Locale;

public class IntegerFormatter extends BaseNumberFormatter{

    private static final String INT_PATTERN = "#,###";
    public IntegerFormatter() {
        super(INT_PATTERN);
    }

    @Override
    public Integer parse(String text, Locale locale) {
        return super.parse(text, locale).intValue();
    }
}
