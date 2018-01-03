package com.qr.demo.previewactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qr.demo.R;
import com.qr.demo.activity.BaseActivity;
import com.qr.demo.activity.PrintActivity;
import com.qr.demo.db.SaveHelper;
import com.qr.demo.model.PrintModel;
import com.qr.demo.utils.ToastUtils;
import com.qr.demo.utils.Utils;
import com.qr.demo.view.CustomFontsTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/12/29.
 * 持挂失补车票中途下车到站退款
 */

public class CgspbcpztxcPreviewActivity extends BaseActivity {

    PrintModel printModel;

    @BindView(R.id.recordThing)
    TextView recordThing;

    @BindView(R.id.connectStation)
    TextView connectStation;

    CustomFontsTextView description;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_replace0);
        description = findViewById(R.id.description);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initListener() {
        save();
    }

    @Override
    protected void initData() {

        printModel = (PrintModel) getIntent().getSerializableExtra("data");

        recordThing.setText("记录事由:" + printModel.recordThing);

        connectStation.setText(printModel.connectStation + "站（中途下车站）:");

        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" + printModel.trainNum + "次列车," + printModel.connectStation + "站开车后，" +
                "旅客" + printModel.name + ",身份证号码" + printModel.cardNum + ",持" + printModel.beginStation + "站至" + printModel.stopStation + "站车票，"
                + printModel.carriageNum + "车" + printModel.seatNum + "号席（铺）位," +
                "票号" + printModel.ticketNum + ",要求在你站下车，经确认席位使用正常，可办理退票，现交你站，请按章办理。";

        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }

    public void save() {
        View v = findViewById(R.id.save);
        if (v != null) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    printModel.saveRecordThing = recordThing.getText().toString();
                    printModel.saveConnectStation = connectStation.getText().toString();
                    printModel.savedescription = description.getText().toString();
                    printModel.uuid = Utils.getMyUUID();

                    try {
                        Gson gson = new Gson();
                        String jsonStr = gson.toJson(printModel);
                        SaveHelper.insert(mContext, jsonStr, printModel.uuid);
                        ToastUtils.show(mContext, "数据保存成功");
                    } catch (Exception e) {
                        ToastUtils.show(mContext, "数据保存失败");
                    }

                    Intent mIntent = new Intent(mContext, PrintActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("data", printModel);
                    mIntent.putExtras(mBundle);
                    finish();
                }
            });
        }
    }


}
