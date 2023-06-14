package com.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationRegex {

    // 비밀번호 포맷 확인(영문, 특수문자, 숫자 포함 8자 이상)
    public static boolean isRegexPassword(String target){
        String regex = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,}$";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(target);
        return matcher.find();
    }
}
