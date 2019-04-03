package com.example.sundemocaculator.util;

public class NumberUtils {

    public static String getResult(double number) {
        return (number > 1e10 || number != (long) number) ? number + "" : (long) number + "";


    }
}
