package com.qr.demo.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sun on 2017/12/28.
 */


public class DbHelper {

    /**
     * 获取车次
     */

    public static String getTrainNum(Context context) {

        String trainCode = "";
        DBManager manager = DBManager.getInstance(context);

        SQLiteDatabase sqLiteDatabase = manager.openDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select train_code from ZC_train_dir_1307", null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            trainCode = cursor.getString(cursor.getColumnIndex("train_code"));
        }
        if (cursor != null) {
            cursor.close();
        }
        sqLiteDatabase.close();
        return trainCode;
    }

    /**
     * 获取车站列表
     */

    /**
     * 获取车厢号列表
     */
}
