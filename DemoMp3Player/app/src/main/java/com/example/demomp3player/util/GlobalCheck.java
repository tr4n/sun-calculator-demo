package com.example.demomp3player.util;

public class GlobalCheck {

    public static boolean checkNonNull(Object object) {
        return null != object;
    }

    public static boolean checkNonNull(Object first, Object second) {
        return (null != first && null != second);
    }
}
