package org.abondar.industrial.formatter.type;

import java.util.Locale;

public class FloatFormatter extends BaseNumberFormatter {

    private static final String FLOAT_PATTERN = "#,##0.00";

    public FloatFormatter(){
        super(FLOAT_PATTERN);
    }

    @Override
    public Float parse(String text, Locale locale) {
        return  super.parse(text, locale).floatValue();
    }
}
