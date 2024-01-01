package org.abondar.industrial.formatter.type;

import java.util.Locale;

public class CurrencyFormatter extends BaseNumberFormatter {

    private static final String CURRENCY_PATTERN = "¤#,##0.00";

    public CurrencyFormatter(){
        super(CURRENCY_PATTERN);
    }

    @Override
    public Double parse(String text, Locale locale) {
       return  super.parse(text, locale).doubleValue();
    }
}
