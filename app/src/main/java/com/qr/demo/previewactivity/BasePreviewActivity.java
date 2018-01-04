package com.qr.demo.previewactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qr.demo.R;
import com.qr.demo.activity.BaseActivity;
import com.qr.demo.activity.PrintActivity;
import com.qr.demo.db.SaveHelper;
import com.qr.demo.event.KillBaseCommonList;
import com.qr.demo.model.PrintModel;
import com.qr.demo.utils.ToastUtils;
import com.qr.demo.utils.Utils;
import com.qr.demo.view.CustomFontsTextView;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by sun on 2018/1/3.
 */

public class BasePreviewActivity extends BaseActivity {

    protected PrintModel printModel;
    protected TextView recordThing;
    protected TextView connectStation;
    protected CustomFontsTextView description;
    protected EditText replace1;
    protected EditText replace2;
    protected EditText replace3;
    protected TextView type;
    protected TextView zhusong;
    protected TextView chasong;


    protected boolean isEditStatus;


    @Override
    protected void setContentView() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initData() {

    }

    public void save() {

        View save = findViewById(R.id.save);

        if (save != null) {
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (printModel != null) {

                        if (recordThing != null) {
                            printModel.saveRecordThing = recordThing.getText().toString();
                        }
                        if (type != null) {
                            printModel.saveRecordThing = type.getText().toString();
                        }
                        if (connectStation != null) {
                            printModel.saveConnectStation = connectStation.getText().toString();
                        }
                        if (zhusong != null) {
                            printModel.saveZhusongDianBao = zhusong.getText().toString();
                        }
                        if (chasong != null) {
                            printModel.saveChaosongDianBao = chasong.getText().toString();
                        }
                        if (description != null) {
                            printModel.savedescription = description.getText().toString();
                        }

                        if (replace1 != null) {
                            printModel.replace1 = replace1.getText().toString();
                        }
                        if (replace2 != null) {
                            printModel.replace2 = replace2.getText().toString();
                        }
                        if (replace3 != null) {
                            printModel.replace3 = replace3.getText().toString();
                        }

                        if (isEditStatus) {
                            //这里处理更新操作
                            try {
                                Gson gson = new Gson();
                                String jsonStr = gson.toJson(printModel);
                                SaveHelper.update(mContext, jsonStr, printModel.uuid);
                                ToastUtils.show(mContext, "数据更新成功");
                            } catch (Exception e) {
                                ToastUtils.show(mContext, "数据更新失败");
                                return;
                            }
                        } else {
                            //增加操作
                            printModel.uuid = Utils.getMyUUID();
                            printModel.saveCreateTime = System.currentTimeMillis();
                            try {
                                Gson gson = new Gson();
                                String jsonStr = gson.toJson(printModel);
                                SaveHelper.insert(mContext, jsonStr, printModel.uuid);
                                ToastUtils.show(mContext, "数据保存成功");
                            } catch (Exception e) {
                                ToastUtils.show(mContext, "数据保存失败");
                                return;
                            }
                        }

                        Intent mIntent = new Intent(mContext, PrintActivity.class);
                        Bundle mBundle = new Bundle();
                        mBundle.putSerializable("data", printModel);
                        mIntent.putExtras(mBundle);
                        startActivity(mIntent);
                        EventBus.getDefault().post(new KillBaseCommonList());
                        finish();
                    }
                }
            });
        }


    }
}
