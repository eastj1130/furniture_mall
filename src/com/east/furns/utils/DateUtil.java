package com.east.furns.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    public static String getDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yy/MM/dd");
        Date date = new Date();
        String format = simpleDateFormat.format(date);
        return format;
    }
}
