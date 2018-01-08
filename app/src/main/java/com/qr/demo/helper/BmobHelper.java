package com.qr.demo.helper;

import android.content.Context;
import android.util.Log;

import com.qr.demo.db.SaveHelper;
import com.qr.demo.model.PrintModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cn.bmob.v3.BmobBatch;
import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BatchResult;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListListener;

/**
 * Created by sun on 2018/1/8.
 */

public class BmobHelper {

    public static void add(Context mContext) {


        List<BmobObject> printModels = null;
        try {
            printModels = SaveHelper.getBmobPrintModelData(mContext);

            if (printModels == null) {
                return;
            }
            Collections.reverse(printModels);

            new BmobBatch().insertBatch(printModels).doBatch(new QueryListListener<BatchResult>() {

                @Override
                public void done(List<BatchResult> o, BmobException e) {
                    if (e == null) {
                        for (int i = 0; i < o.size(); i++) {
                            BatchResult result = o.get(i);
                            BmobException ex = result.getError();
                            if (ex == null) {
                                log("第" + i + "个数据批量添加成功：" + result.getCreatedAt() + "," + result.getObjectId() + "," + result.getUpdatedAt());
                            } else {
                                log("第" + i + "个数据批量添加失败：" + ex.getMessage() + "," + ex.getErrorCode());
                            }
                        }
                    } else {
                        Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                    }
                }
            });


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private static void log(String s) {
        Log.i("bmob", s);
    }
}
