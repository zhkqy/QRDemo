package com.qr.demo.utils;

import android.app.Activity;


import com.qr.demo.R;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.UUID;

/**
 * Created by chenlei on 2016/10/9.
 */
public class Utils {

    public static int getImageResourceId(String name) {
        R.drawable drawables=new R.drawable();
        //默认的id
        int resId=0;
        try {
            //根据字符串字段名，取字段//根据资源的ID的变量名获得Field的对象,使用反射机制来实现的
            java.lang.reflect.Field field=R.drawable.class.getField(name);
            //取值
            resId=(Integer)field.get(drawables);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resId;
    }

    public static String getFromRaw(int rawId,Activity activity) {
        String Result = "";
        try {
            InputStreamReader inputReader = new InputStreamReader(activity.getResources().openRawResource(rawId));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null)
                Result += line;
            return Result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result;
    }

    /**
     * 获取随机的uuid
     */
    public static String getMyUUID(){
        UUID uuid = UUID.randomUUID();
        String uniqueId = uuid.toString();
        return uniqueId;
    }


    public static String doubleTrans(double d) {
        if (Math.round(d) - d == 0) {
            return String.valueOf((long) d);
        }
        return String.valueOf(d);
    }

}
