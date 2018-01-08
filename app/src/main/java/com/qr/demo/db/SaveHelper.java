package com.qr.demo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.Gson;
import com.qr.demo.model.PrintModel;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by sun on 2018/1/3.
 */

public class SaveHelper {


    /**
     * 插入数据
     */
    public static long insert(Context mContext, String content, String uuid) {
        ContentValues values = new ContentValues();
        values.put(SqlLiteHelper.CONTENT, content);
        values.put(SqlLiteHelper.UUID, uuid);

        long rowid = SqlLiteHelper.getInstance(mContext).insert(SqlLiteHelper.DATABASE_NAME, SqlLiteHelper.ID, values);
        return rowid;

    }


    /**
     * @param context 获取列表
     * @return
     */
    public synchronized static List<PrintModel> getPrintModelData(Context context) throws Exception {
        SqlLiteHelper dbHelper = SqlLiteHelper.getInstance(context);
        List<PrintModel> list = new ArrayList<PrintModel>();

        Cursor cursor = dbHelper.select(SqlLiteHelper.DATABASE_NAME, null,
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                String content = cursor.getString(cursor.getColumnIndex(SqlLiteHelper.CONTENT));
                try {
                    PrintModel printModel = new Gson().fromJson(content, PrintModel.class);
                    list.add(printModel);
                } catch (Exception e) {

                }
                cursor.moveToNext();
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        dbHelper.close();
        return list;
    }

    public static void update(Context mContext, String content, String uuid) {
        ContentValues values = new ContentValues();

        values.put(SqlLiteHelper.CONTENT, content);
        String where = SqlLiteHelper.UUID + "=?";
        String wherearg[] = new String[]{uuid};

        SqlLiteHelper.getInstance(mContext).update(SqlLiteHelper.DATABASE_NAME, values, where, wherearg);
    }


    public synchronized static void delete(Context context, String uuid) {
        SqlLiteHelper dbHelper = SqlLiteHelper.getInstance(context);
        String delete = SqlLiteHelper.UUID + "=?";
        String wherearg[] = new String[]{uuid};
        dbHelper.delete(SqlLiteHelper.DATABASE_NAME, delete, wherearg);
    }



//    bmob



    /**
     * @param context 获取列表
     * @return
     */
    public synchronized static List<BmobObject> getBmobPrintModelData(Context context) throws Exception {
        SqlLiteHelper dbHelper = SqlLiteHelper.getInstance(context);
        List<BmobObject> list = new ArrayList<BmobObject>();

        Cursor cursor = dbHelper.select(SqlLiteHelper.DATABASE_NAME, null,
                null, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                String content = cursor.getString(cursor.getColumnIndex(SqlLiteHelper.CONTENT));
                try {
                    PrintModel printModel = new Gson().fromJson(content, PrintModel.class);
                    list.add(printModel);
                } catch (Exception e) {

                }
                cursor.moveToNext();
            }
        }
        if (cursor != null) {
            cursor.close();
        }
        dbHelper.close();
        return list;
    }


}
