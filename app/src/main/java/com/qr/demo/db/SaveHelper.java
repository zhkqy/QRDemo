package com.qr.demo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.google.gson.Gson;
import com.qr.demo.bmob.SaveModel;
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
        values.put(SqlLiteHelper.STATUS, SqlLiteHelper.STATUS_ADD);
        values.put(SqlLiteHelper.SYNCH_STATUS, 0);
        values.put(SqlLiteHelper.OBJECTID, 0);

        long rowid = SqlLiteHelper.getInstance(mContext).insert(SqlLiteHelper.DATABASE_NAME, SqlLiteHelper.ID, values);
        return rowid;

    }


    /**
     * @param context 获取列表
     * @return
     */
    public static List<PrintModel> getPrintModelData(Context context) throws Exception {
        SqlLiteHelper dbHelper = SqlLiteHelper.getInstance(context);
        List<PrintModel> list = new ArrayList<PrintModel>();
        String selection = SqlLiteHelper.STATUS + "!=-1";
        Cursor cursor = dbHelper.select(SqlLiteHelper.DATABASE_NAME, null,
                selection, null, null, null);

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
        values.put(SqlLiteHelper.STATUS, SqlLiteHelper.STATUS_UPDATE);
        SqlLiteHelper.getInstance(mContext).update(SqlLiteHelper.DATABASE_NAME, values, where, wherearg);
    }


    public static void updateStatus(Context mContext, int status, String uuid) {
        ContentValues values = new ContentValues();
        values.put(SqlLiteHelper.STATUS, status);
        values.put(SqlLiteHelper.SYNCH_STATUS, 1);
        String where = SqlLiteHelper.UUID + "=?";
        String wherearg[] = new String[]{uuid};
        SqlLiteHelper.getInstance(mContext).update(SqlLiteHelper.DATABASE_NAME, values, where, wherearg);
    }


    public static void updateStatusAndObj(Context mContext, int status, String objectId, String uuid) {
        ContentValues values = new ContentValues();
        values.put(SqlLiteHelper.STATUS, status);
        values.put(SqlLiteHelper.SYNCH_STATUS, 1);
        values.put(SqlLiteHelper.OBJECTID, objectId);
        String where = SqlLiteHelper.UUID + "=?";
        String wherearg[] = new String[]{uuid};
        SqlLiteHelper.getInstance(mContext).update(SqlLiteHelper.DATABASE_NAME, values, where, wherearg);
    }


    //    假删除
    public static void delete(Context context, String uuid) {
//        SqlLiteHelper dbHelper = SqlLiteHelper.getInstance(context);
//        String delete = SqlLiteHelper.UUID + "=?";
//        String wherearg[] = new String[]{uuid};
//        dbHelper.delete(SqlLiteHelper.DATABASE_NAME, delete, wherearg);

        ContentValues values = new ContentValues();
        values.put(SqlLiteHelper.STATUS, SqlLiteHelper.STATUS_DELETE);
        String where = SqlLiteHelper.UUID + "=?";
        String wherearg[] = new String[]{uuid};
        SqlLiteHelper.getInstance(context).update(SqlLiteHelper.DATABASE_NAME, values, where, wherearg);
    }

//    bmob

    public static List<BmobObject> getBmobAddData(Context context) throws Exception {
        SqlLiteHelper dbHelper = SqlLiteHelper.getInstance(context);
        List<BmobObject> list = new ArrayList<BmobObject>();
        String selection = "(" + SqlLiteHelper.STATUS + "==0 or " + SqlLiteHelper.STATUS + "==1) and " + SqlLiteHelper.SYNCH_STATUS + "==0";
        Cursor cursor = dbHelper.select(SqlLiteHelper.DATABASE_NAME, null,
                selection, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                String content = cursor.getString(cursor.getColumnIndex(SqlLiteHelper.CONTENT));
                try {
                    PrintModel printModel = new Gson().fromJson(content, PrintModel.class);

                    SaveModel saveModel = new SaveModel();
                    saveModel.recordThing = printModel.recordThing;//  记录事由
                    saveModel.recordtime = printModel.year + "-" + printModel.month + "-" + printModel.day;//记录时间
                    saveModel.trainNum = printModel.trainNum;//车次
                    saveModel.uuid = printModel.uuid;
                    saveModel.content = content;

                    list.add(saveModel);
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


    public static List<BmobObject> getBmobUpdateData(Context context) throws Exception {
        SqlLiteHelper dbHelper = SqlLiteHelper.getInstance(context);
        List<BmobObject> list = new ArrayList<BmobObject>();
        String selection = SqlLiteHelper.STATUS + "==1 and " + SqlLiteHelper.SYNCH_STATUS + "==1";

        Cursor cursor = dbHelper.select(SqlLiteHelper.DATABASE_NAME, null,
                selection, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                String objectId = cursor.getString(cursor.getColumnIndex(SqlLiteHelper.OBJECTID));
                String content = cursor.getString(cursor.getColumnIndex(SqlLiteHelper.CONTENT));
                try {
                    PrintModel printModel = new Gson().fromJson(content, PrintModel.class);

                    SaveModel saveModel = new SaveModel();
                    saveModel.recordThing = printModel.recordThing;//  记录事由
                    saveModel.recordtime = printModel.year + "-" + printModel.month + "-" + printModel.day;//记录时间
                    saveModel.trainNum = printModel.trainNum;//车次
                    saveModel.uuid = printModel.uuid;
                    saveModel.content = content;
                    saveModel.setObjectId(objectId);

                    list.add(saveModel);
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


    public static List<BmobObject> getBmobDeleteData(Context context) throws Exception {
        SqlLiteHelper dbHelper = SqlLiteHelper.getInstance(context);
        List<BmobObject> list = new ArrayList<BmobObject>();
        String selection = SqlLiteHelper.STATUS + "==-1";
        Cursor cursor = dbHelper.select(SqlLiteHelper.DATABASE_NAME, null,
                selection, null, null, null);

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
