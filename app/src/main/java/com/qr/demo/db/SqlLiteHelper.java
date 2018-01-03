package com.qr.demo.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public class SqlLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;// 数据库版本号
    public static final String DATABASE_NAME = "SAVEDB";// 数据库名称

    private static SqlLiteHelper dbHelper;
    public static String ID = "id";
    public static String UUID = "uuid";
    public static String CONTENT = "content";

    public static synchronized SqlLiteHelper getInstance(Context context) {
        if (dbHelper == null) {
            if (context != null) {
                context = context.getApplicationContext();
            }
            dbHelper = new SqlLiteHelper(context, DATABASE_NAME, null,
                    DATABASE_VERSION);
        }
        return dbHelper;
    }

    private SqlLiteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                          int version) {
        super(context, name, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE_NAME + " (" + ID
                + " INTEGER PRIMARY KEY AUTOINCREMENT" + ", " + CONTENT + " str, " + UUID + " string);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		db.execSQL("DROP TABLE IF EXISTS " + SELF_BILL);
//		onCreate(db);
    }

    public long insert(String table, String id, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long l = db.insert(table, id, values);
        db.close();
        return l;
    }

    public int update(String table, ContentValues values, String where,
                      String[] whereArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int n = db.update(table, values, where, whereArgs);
        db.close();
        return n;
    }

    public int delete(String table, String where, String[] whereArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int n = db.delete(table, where, whereArgs);
        db.close();
        return n;
    }

    public Cursor select(String table, String[] columns, String selection,
                         String[] selectionArgs, String orderBy, String limit) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        return db.query(table, columns, selection, selectionArgs, null, null,
                orderBy, limit);
    }

    public void replace(String table, List<ContentValues> values) {
        if (values == null) {
            return;
        }
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.beginTransaction();
        for (ContentValues value : values) {
            db.replace(table, null, value);
        }
        db.setTransactionSuccessful();
        db.endTransaction();
        db.close();
    }
}
