package org.abondar.industrial.formatter.type;

import org.abondar.industrial.formatter.exception.FormatException;
import org.abondar.industrial.formatter.validation.ValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BaseTextFormatter {

    private static final Logger log = LoggerFactory.getLogger(BaseTextFormatter.class);
    private static final String DEFAULT_FORMAT ="\\s+";

    protected String format(String input,String format) {
        if (!ValidationUtil.validateInput(input) && !ValidationUtil.validateInput(format)){
            throw new FormatException("Wrong format input");
        }

        Pattern pattern = Pattern.compile(format);
        Matcher matcher = pattern.matcher(input);

        if (matcher.find()) {
            log.info("Parsed input {}",input);
            return matcher.group();
        } else {
            throw new FormatException("Text can't be formatted");
        }
    }

    protected String format(String input) {
        if (!ValidationUtil.validateInput(input)){
            throw new FormatException("Wrong format input");
        }

        log.info("Formatted input {}",input);
        return input.replaceAll(DEFAULT_FORMAT, "");

    }


}
