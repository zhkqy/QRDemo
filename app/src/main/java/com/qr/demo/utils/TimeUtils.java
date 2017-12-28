package com.qr.demo.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenlei on 2016/10/8.
 */
public class TimeUtils {


    public static String longConvertDate(Long time) {
        //时间戳转化为Sting或Date
        String d = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            d = format.format(time);
            return d;
        } catch (Exception e) {
        }
        return "";
    }

    public static String longConvertDateDetail(Long time) {
        //时间戳转化为Sting或Date
        String d = "";
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm");
            d = format.format(time);
            return d;
        } catch (Exception e) {
        }
        return "";
    }

    public static long calendarConvertLong(Calendar calendar) {
        Date d = calendar.getTime();
        long l = d.getTime();
        return l;
    }

    public static String getCurrentTime() {

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        String str = formatter.format(curDate);

        return str;
    }


    // strTime要转换的String类型的时间
    // formatType时间格式
    // strTime的时间格式和formatType的时间格式必须相同
    public static long stringToLong(String strTime, String formatType)
            throws ParseException {
        Date date = stringToDate(strTime, formatType); // String类型转成date类型
        if (date == null) {
            return 0;
        } else {
            long currentTime = dateToLong(date); // date类型转成long类型
            return currentTime;
        }
    }


    // strTime要转换的string类型的时间，formatType要转换的格式yyyy-MM-dd HH:mm:ss//yyyy年MM月dd日
    // HH时mm分ss秒，
    // strTime的时间格式必须要与formatType的时间格式相同
    public static Date stringToDate(String strTime, String formatType)
            throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(formatType);
        Date date = null;
        date = formatter.parse(strTime);
        return date;
    }

    // date要转换的date类型的时间
    public static long dateToLong(Date date) {
        return date.getTime();
    }



}
