package com.qr.demo.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.qr.demo.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class DBManager {
    private final int BUFFER_SIZE = 400000;
    public static final String DB_NAME = "ZCDB.db"; //保存的数据库文件名
    public static final String PACKAGE_NAME = "com.yilong.qrlabel";
    public static final String DB_PATH = "/data"
            + Environment.getDataDirectory().getAbsolutePath() + "/" + PACKAGE_NAME;  //在手机里存放数据库的位置

    private SQLiteDatabase database;
    private Context context;

    public DBManager(Context context) {
        this.context = context;
    }

    public void openDatabase() {
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
    }

    private SQLiteDatabase openDatabase(String dbfile) {
        try {

            String preDb = getInnerDataPath();

            if (new File(preDb).exists()) {

                File f = new File(dbfile);
                if (f.exists()) {
                    f.delete();
                }
                InputStream is = new FileInputStream(new File(preDb));
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                }
                fos.close();
                is.close();

                SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
                        null);
                return db;
            } else {
                Log.e("Database", "本地数据库不存在");
            }
        } catch (FileNotFoundException e) {
            Log.e("Database", "File not found");
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("Database", "IO exception");
            e.printStackTrace();
        }
        return null;
    }

    public void closeDatabase() {

        if (database != null) {
            this.database.close();
        }
    }


    public String getInnerDataPath() {
        return Environment.getExternalStorageDirectory()+"/data/" + DB_NAME;
    }

}