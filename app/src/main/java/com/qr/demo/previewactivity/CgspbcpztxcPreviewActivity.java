package com.qr.demo.previewactivity;

import android.view.View;
import android.widget.TextView;

import com.qr.demo.R;
import com.qr.demo.model.PrintModel;
import com.qr.demo.view.CustomFontsTextView;

import butterknife.OnClick;

/**
 * Created by sun on 2017/12/29.
 * 持挂失补车票中途下车到站退款
 */

public class CgspbcpztxcPreviewActivity extends BasePreviewActivity {

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_replace0);
        description = (CustomFontsTextView) findViewById(R.id.description);
        connectStation = (TextView) findViewById(R.id.connectStation);
        recordThing = (TextView) findViewById(R.id.recordThing);
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

        recordThing.setText(printModel.recordThing);

        connectStation.setText(printModel.connectStation + "站:");

        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" + printModel.trainNum + "次列车," + printModel.troubleStation + "站开车后，" +
                "旅客" + printModel.name + ",身份证号码" + printModel.cardNum + ",持" + printModel.beginStation + "站至" + printModel.stopStation + "站车票，"
                + printModel.chexiang + "席（铺）位," +
                "票号" + printModel.ticketNum + ",要求在你站下车，经确认席位使用正常，可办理退票，现交你站，请按章办理。";

        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }

}
