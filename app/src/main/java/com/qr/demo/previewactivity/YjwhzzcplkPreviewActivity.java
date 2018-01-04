package com.qr.demo.previewactivity;

import android.view.View;
import android.widget.TextView;

import com.qr.demo.R;
import com.qr.demo.activity.BaseActivity;
import com.qr.demo.model.PrintModel;
import com.qr.demo.view.CustomFontsTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/12/29.
 * 移交未换纸质车票旅客
 */

public class YjwhzzcplkPreviewActivity extends BasePreviewActivity {


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_replace0);
        recordThing = findViewById(R.id.recordThing);
        connectStation = findViewById(R.id.connectStation);
        description = findViewById(R.id.description);
        isEditStatus = getIntent().getBooleanExtra("isEditStatus", false);
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

        connectStation.setText(printModel.connectStation + "站:");


        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" + printModel.trainNum + "次列车," + printModel.connectStation + "站开车后，" +
                "旅客" + printModel.name + ",身份证号码" + printModel.cardNum + ",持手机购票短信乘车（订单号码" + printModel.netOrderNum + "）," +
                "自述因" + printModel.netErrorReason + ",造成网购" + printModel.netBeginStation + "站至" + printModel.netStopStation + "站车票，"
                + printModel.carriageNum + "车" + printModel.seatNum + "号席（铺）位," +
                "未能换取纸质车票，经列车通过站车交互系统查询情况属实，现交你站，请按章办理。";

        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }

}
