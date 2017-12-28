package com.qr.demo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sun on 2017/12/28.
 */


public class DbHelper {

    /**
     * 获取车次
     */

    public static String getTrainNum(Context context) {
        DBManager manager = DBManager.getInstance(context);

        SQLiteDatabase sqLiteDatabase = manager.openDatabase();

//        if (sqLiteDatabase != null) {
//            sqLiteDatabase.query();
//
//        }
        return "";
    }

    /**
     * 获取车站列表
     */

    /**
     * 获取车厢号列表
     */
}
