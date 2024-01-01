package org.abondar.industrial.formatter.type;

import org.abondar.industrial.formatter.Formatter;
import org.abondar.industrial.formatter.exception.FormatException;
import org.abondar.industrial.formatter.validation.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class TimeFormatter implements Formatter<LocalTime> {

    private static final Logger log = LoggerFactory.getLogger(TimeFormatter.class);

    private static final String DEFAULT_FORMAT = "HH:mm";

    private static final String VALIDATION_PATTERN = "^(?:\\d{2}:\\d{2}(?::\\d{2})?|\\d{4}(?::\\d{2})?|(?:\\d{3}|\\d{6}))$";

    private static final String AMPM_PATTERN =  "^(1[0-2]|0?[1-9])(:[0-5][0-9])? ?([aA|pP][mM])$";


    public LocalTime parse(String text, Locale locale) throws FormatException {
        var patterns = List.of(
                "Hmm",
                "H:mm",
                "HH:mm",
                "HH:mm:ss",
                "h a",
                "hh:mm a",
                "h:mm a"
        );

        LocalTime res = null;

        for (String p : patterns) {
            try {
                var formatter = DateTimeFormatter.ofPattern(p);
                res = LocalTime.parse(text, formatter);
            } catch (Exception ignored) {

            }
        }

        if (res == null){
            throw new FormatException("Error Parsing provided time");
        }
        log.info("Parsed time {}",text);
        return res;
}

    @Override
    public String format(LocalTime object, Locale locale) throws FormatException {
        return performFormat(object, DEFAULT_FORMAT);
    }

    @Override
    public String format(LocalTime object, String formatExp, Locale locale) throws FormatException {
        return performFormat(object, formatExp);
    }

    private String performFormat(LocalTime time, String timeFormat) throws FormatException {
        try {
            log.info("Formatting to format {}",timeFormat);
            var timeFormatter = DateTimeFormatter.ofPattern(timeFormat);
            return timeFormatter.format(time);
        } catch (Exception ex) {
            log.error(ex.getMessage());
            throw new FormatException("Error Formatting provided date");
        }

    }

    @Override
    public boolean isValid(String text) {
        return ValidationUtil.validateString(text, VALIDATION_PATTERN) || ValidationUtil.validateString(text,AMPM_PATTERN);
    }
}
