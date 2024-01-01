package org.abondar.industrial.formatter.type;

import org.abondar.industrial.formatter.Formatter;
import org.abondar.industrial.formatter.validation.ValidationUtil;
import org.abondar.industrial.formatter.exception.FormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class BaseNumberFormatter implements Formatter<Number> {

    private static final String NUMBER_REGEX = "^(?:\\p{Sc})?[0-9.,]+$";

    private static final Logger log = LoggerFactory.getLogger(BaseNumberFormatter.class);

    private final String defaultPattern;

    public BaseNumberFormatter(String defaultPattern) {
        this.defaultPattern = defaultPattern;
    }


    @Override
    public Number parse(String text, Locale locale) throws FormatException {
        try {
            var numberFormat = new DecimalFormat(defaultPattern, DecimalFormatSymbols.getInstance(locale));
            log.info("Parsed number {}",text);
            return numberFormat.parse(text);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new FormatException("Error parsing number: " + e.getMessage());
        }
    }

    @Override
    public String format(Number number, Locale locale) throws FormatException {
        return performFormat(number,defaultPattern,locale);
    }

    @Override
    public String format(Number number, String pattern, Locale locale) throws FormatException {
      return performFormat(number,pattern,locale);
    }

    private String performFormat(Number number, String pattern, Locale locale) throws FormatException {
        try {
            var numberFormat = new DecimalFormat(pattern, DecimalFormatSymbols.getInstance(locale));
            log.info("Formatted number {}",number);
            return numberFormat.format(number);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new FormatException("Error formatting number: " + e.getMessage());
        }
    }


    @Override
    public boolean isValid(String text) {
        return ValidationUtil.validateString(text, NUMBER_REGEX);
    }
}
