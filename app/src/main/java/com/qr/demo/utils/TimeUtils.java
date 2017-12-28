package com.qr.demo.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by chenlei on 2016/10/8.
 */
public class TimeUtils {


    public static String longConvertDate(Long time){
        //时间戳转化为Sting或Date
        String d = "";
        try {
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
             d = format.format(time);
            return d;
        }catch (Exception e){
        }
        return "";
    }

    public static String longConvertDateDetail(Long time){
        //时间戳转化为Sting或Date
        String d = "";
        try {
            SimpleDateFormat format =  new SimpleDateFormat("yyyy.MM.dd HH:mm");
            d = format.format(time);
            return d;
        }catch (Exception e){
        }
        return "";
    }

    public static long calendarConvertLong(Calendar calendar){
        Date d = calendar.getTime();
        long l = d.getTime();
        return l;
    }


//    public static YearMonthModel getYearMonthTimeStamp(int year,int month){
//
//        YearMonthModel resultPoint = new YearMonthModel();
//        long left=0;
//        long right=0;
//
//        /**
//         * 设置当月月初的时间戳
//         */
//        //获取当前时间
//        Calendar calLeft = Calendar.getInstance();
//        //下面可以设置月份，注：月份设置要减1，所以设置1月就是1-1，设置2月就是2-1，如此类推
//        calLeft.set(Calendar.YEAR,year);
//        calLeft.set(Calendar.MONTH, month - 1);
//
//        calLeft.set(calLeft.get(Calendar.YEAR), calLeft.get(Calendar.MONTH), 1, 00, 00, 00);
//
//        //按格式输出
//        SimpleDateFormat SSSS = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
//        System.out.println(SSSS.format(calLeft.getTime()));
//
//
//        Calendar calRight = Calendar.getInstance();
//        //下面可以设置月份，注：月份设置要减1，所以设置1月就是1-1，设置2月就是2-1，如此类推
//        calRight.set(Calendar.YEAR,year);
//        calRight.set(Calendar.MONTH, month-1);
//        int MaxDay=calRight.getActualMaximum(Calendar.DAY_OF_MONTH);
//        //按你的要求设置时间
//        calRight.set( calRight.get(Calendar.YEAR), calRight.get(Calendar.MONTH), MaxDay, 23, 59, 59);
//
//        //按格式输出
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
//        System.out.println(sdf.format(calRight.getTime()));
//
//        left = TimeUtils.calendarConvertLong(calLeft);
//        right=TimeUtils.calendarConvertLong(calRight);
//        resultPoint.setLeft(left);
//        resultPoint.setRight(right);
//
//      return resultPoint;
//    }

}
