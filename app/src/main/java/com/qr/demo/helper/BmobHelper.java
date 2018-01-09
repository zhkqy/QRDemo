package com.qr.demo.helper;

import android.content.Context;
import android.util.Log;

import com.qr.demo.bmob.SaveModel;
import com.qr.demo.db.SaveHelper;
import com.qr.demo.db.SqlLiteHelper;
import com.qr.demo.utils.ToastUtils;

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

    public static void synchAdd(final Context mContext) {

        List<BmobObject> printModels = null;
        try {
            printModels = SaveHelper.getBmobAddData(mContext);
            if (printModels == null || printModels.size() == 0) {
                return;
            }
            Collections.reverse(printModels);

            boolean flag = true;
            while (flag) {
                List<BmobObject> tempList = new ArrayList<>();
                if (printModels.size() > 50) {
                    for (int x = 0; x < 50; x++) {
                        tempList.add(printModels.remove(0));
                    }
                } else {
                    tempList = printModels;
                    flag = false;
                }

                final List<BmobObject> finalPrintModels = tempList;
                new BmobBatch().insertBatch(finalPrintModels).doBatch(new QueryListListener<BatchResult>() {

                    @Override
                    public void done(List<BatchResult> o, BmobException e) {
                        if (e == null) {
                            for (int i = 0; i < o.size(); i++) {
                                BatchResult result = o.get(i);
                                BmobException ex = result.getError();
                                if (ex == null) {
                                    SaveModel p = ((SaveModel) finalPrintModels.get(i));
                                    SaveHelper.updateStatusAndObj(mContext, SqlLiteHelper.STATUS_SYNCH, result.getObjectId(), p.uuid);
                                    ToastUtils.show(mContext, "数据同步成功" + i);
                                } else {
                                    ToastUtils.show(mContext, "数据同步失败" + i);
                                }
                            }
                        } else {
                            Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void synchUpdate(final Context mContext) {

        List<BmobObject> printModels = null;
        try {
            printModels = SaveHelper.getBmobUpdateData(mContext);
            if (printModels == null || printModels.size() == 0) {
                return;
            }
            Collections.reverse(printModels);

            boolean flag = true;
            while (flag) {
                List<BmobObject> tempList = new ArrayList<>();
                if (printModels.size() > 50) {
                    for (int x = 0; x < 50; x++) {
                        tempList.add(printModels.remove(0));
                    }
                } else {
                    tempList = printModels;
                    flag = false;
                }

                final List<BmobObject> finalPrintModels = tempList;
                new BmobBatch().updateBatch(finalPrintModels).doBatch(new QueryListListener<BatchResult>() {

                    @Override
                    public void done(List<BatchResult> o, BmobException e) {
                        if (e == null) {
                            for (int i = 0; i < o.size(); i++) {
                                BatchResult result = o.get(i);
                                BmobException ex = result.getError();
                                if (ex == null) {
                                    SaveModel p = ((SaveModel) finalPrintModels.get(i));
                                    SaveHelper.updateStatus(mContext, SqlLiteHelper.STATUS_SYNCH, p.uuid);
                                    ToastUtils.show(mContext, "数据更新成功" + i);
                                } else {
                                    ToastUtils.show(mContext, "数据更新失败" + i);
                                }
                            }
                        } else {
                            Log.i("bmob", "失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
