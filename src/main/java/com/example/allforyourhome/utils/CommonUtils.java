package com.example.allforyourhome.utils;


import com.example.allforyourhome.exceptions.RestException;

public class CommonUtils {

    public static String convertToLowerAndAddUnderscore(String str) {
        StringBuilder sb = new StringBuilder(str.length() + 6);
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                sb.append("_");
                sb.append(Character.toLowerCase(c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void checkPageAndSize(int page, int size) {
        if (page < 0 || size < 1)
            throw RestException.restThrow(MessageConstants.PAGE_OR_SIZE_ERROR);
    }
}
