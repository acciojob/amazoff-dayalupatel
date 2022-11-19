package com.driver.Helper;

public class TimeConverter {
    // String To Integer
    public static int convertTimeStringToInt(String time) {
        int HH = getTime(time.charAt(0), time.charAt(1));
        int MM = getTime(time.charAt(3), time.charAt(4));

        return HH*60 + MM;
    }
    static int getTime(char first, char second) {
        int time = (first-'0')*10;
        time += (second-'0');
        return time;
    }

    // Integer To String
    public static String convertTimeIntToString(int time) {
        int MM = time%60;
        int HH = time/60;
        return HH + ":" + MM;
    }
}
