package org.abondar.industrial.formatter.type;

import org.abondar.industrial.formatter.validation.ValidationUtil;
import org.abondar.industrial.formatter.exception.FormatException;
import org.abondar.industrial.formatter.Formatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateFormatter implements Formatter<Date> {

    private static final Logger log = LoggerFactory.getLogger(DateFormatter.class);

    private static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";

    private static final String VALIDATION_PATTERN = "^(?:(?:(?:0?[13578]|1[02])[-/](0?[1-9]|[12]\\d|3[01])" +
            "|(?:0?[469]|11)[-/](0?[1-9]|[12]\\d|30)|0?2[-/](0?[1-9]|1\\d|2[0-9]))" +
            "[-/]\\d{2,4}|\\d{2,4}[-/](0?[1-9]|1[0-2])[-/](0?[1-9]|[12]\\d|3[01])|(?:0?[1-9]|[12]\\d|3[01])" +
            "[-/](?:0?[13578]|1[02])[-/]\\d{2,4})$";

    @Override
    public Date parse(String text, Locale locale) throws FormatException {
         var format = new SimpleDateFormat(DEFAULT_DATE_FORMAT,locale);
         try {
             Date date = format.parse(text);
             log.info("Formatted to date {}",date.toString());
             return date;
         } catch (ParseException ex){
             log.error(ex.getMessage());
             throw new FormatException("Error Parsing provided date");
         }
    }

    @Override
    public String format(Date date,Locale locale) throws FormatException {
        return performFormat(date, DEFAULT_DATE_FORMAT, locale);
    }

    @Override
    public String format(Date date, String dateFormat, Locale locale) throws FormatException {
        return performFormat(date, dateFormat, locale);
    }

    private String performFormat(Date date, String dateFormat,Locale locale) throws FormatException {
        try {
            log.info("Formatting to format {}",dateFormat);
            var format = new SimpleDateFormat(dateFormat,locale);
            return format.format(date);
        } catch (Exception ex){
            log.error(ex.getMessage());
            throw new FormatException("Error Formatting provided date");
        }

    }

    @Override
    public boolean isValid(String text) {
        return ValidationUtil.validateString(text,VALIDATION_PATTERN);
    }
}
