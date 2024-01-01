package org.abondar.industrial.formatter.validation;

import java.util.regex.Pattern;

public class ValidationUtil {
    public static boolean validateString(String input, String expression){
        if (!(validateInput(input) && validateInput(expression))){
            return false;
        }

        var pattern = Pattern.compile(expression);
        return pattern.matcher(input).matches();
    }

    public static boolean validateInput(String input){
        if (input==null ){
            return false;
        }

        if (input.isBlank() || input.isEmpty()){
            return false;
        }

        return true;
    }
}
