package org.abondar.industrial.formatter;

import org.abondar.industrial.formatter.exception.FormatterFactoryException;
import org.abondar.industrial.formatter.type.*;

import java.util.HashMap;
import java.util.Map;

public class FormatterFactory {

    private static FormatterFactory instance;
    private final Map<String, Formatter<?>> formatters;

    private FormatterFactory() {
        formatters = new HashMap<>();
        initializeDefaultFormatters();
    }

    public static FormatterFactory getInstance() {
        if (instance == null) {
            synchronized (FormatterFactory.class) {
                if (instance == null) {
                    instance = new FormatterFactory();
                }
            }
        }
        return instance;
    }


    private void initializeDefaultFormatters() {
        formatters.put(Type.DATE.name(),new DateFormatter());
        formatters.put(Type.TIME.name(),new TimeFormatter());
        formatters.put(Type. EMAIL.name(), new EmailFormatter());
        formatters.put(Type.PHONE.name(), new PhoneNumberFormatter());
        formatters.put(Type.SSN.name(), new SsnFormatter());
        formatters.put(Type.BANK_ACCOUNT.name(), new BankAccountFormatter());
        formatters.put(Type.INTEGER_NUMBER.name(), new IntegerFormatter());
        formatters.put(Type.FLOAT_NUMBER.name(), new FloatFormatter());
        formatters.put(Type.CURRENCY.name(), new CurrencyFormatter());
    }

    public  Formatter<?> getFormatter(String type) {
        if (!formatters.containsKey(type)) {
            throw new FormatterFactoryException("Formatter not implemented for type: " + type);
        }
        return formatters.get(type);
    }

    public void registerFormatter(String type, Formatter<?> formatter) {
        formatters.put(type, formatter);
    }
}
