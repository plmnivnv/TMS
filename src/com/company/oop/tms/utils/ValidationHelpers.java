package com.company.oop.tms.utils;

public class ValidationHelpers {


    public static void validateValueInRange(double value, double min, double max, String errorMessage) {
        if (value < min || value > max){
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void validateStringLength(String stringToValidate, int minLength, int maxLength, String errorMessage) {
        validateValueInRange(stringToValidate.length(), minLength, maxLength, errorMessage);
    }



}
