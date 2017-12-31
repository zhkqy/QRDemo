package com.qr.demo.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.qr.demo.model.CarriageNumModel;
import com.qr.demo.model.ZcStopTimeModel;

import java.util.ArrayList;

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

    public static ArrayList<ZcStopTimeModel> getTrainList(Context context) {

        DBManager manager = DBManager.getInstance(context);

        SQLiteDatabase sqLiteDatabase = manager.openDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from ZC_stop_time_1307", null);


        ArrayList<ZcStopTimeModel> zcStopTimeModels = new ArrayList<>();
        zcStopTimeModels.clear();

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                ZcStopTimeModel zcStopTimeModel = new ZcStopTimeModel();

                zcStopTimeModel.station_name = cursor.getString(cursor.getColumnIndex("station_name"));
                zcStopTimeModel.start_train_date = cursor.getString(cursor.getColumnIndex("start_train_date"));

                zcStopTimeModels.add(zcStopTimeModel);
                cursor.moveToNext();
            }
        }

        if (cursor != null) {
            cursor.close();
        }
        sqLiteDatabase.close();
        return zcStopTimeModels;

    }

    /**
     * 获取车厢号列表
     */

    public static ArrayList<CarriageNumModel> getTrainCarriageNum(Context context) {

        DBManager manager = DBManager.getInstance(context);

        SQLiteDatabase sqLiteDatabase = manager.openDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery("select * from ZC_train_seat_1307", null);

        ArrayList<CarriageNumModel> carriageNumModels = new ArrayList<>();
        carriageNumModels.clear();

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {

                CarriageNumModel model = new CarriageNumModel();

                model.carriageNum = cursor.getString(cursor.getColumnIndex("coach_no"));
                model.seatNum = cursor.getString(cursor.getColumnIndex("seat_no"));

                carriageNumModels.add(model);
                cursor.moveToNext();
            }
        }

        if (cursor != null) {
            cursor.close();
        }
        sqLiteDatabase.close();
        return carriageNumModels;

    }
}
