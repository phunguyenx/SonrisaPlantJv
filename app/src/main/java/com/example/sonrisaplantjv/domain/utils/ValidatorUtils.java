package com.example.sonrisaplantjv.domain.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatorUtils {
    public static boolean emptyValue(String str) {
        if (null == str || str.equals("")) {
            return true;
        }
        return false;
    }
    public static boolean isEmail(String Value) {
        Pattern pattern = Pattern.compile(
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher matcher = pattern.matcher(Value);
        return matcher.matches();
    }
    public static boolean isMatch(String s1, String s2){
        return s1.equals(s2);
    }
}
