package com.javaweb.check;

public class Check_Numbers {
    public static boolean checkNumbers(String value) {
        return value != null && value.matches("\\d+");
    }
}
