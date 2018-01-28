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
 * 移交无票人员
 */

public class YjwpryPreviewActivity extends BasePreviewActivity {


    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_replace0);
        recordThing = (TextView) findViewById(R.id.recordThing);
        connectStation = (TextView) findViewById(R.id.connectStation);
        description = (CustomFontsTextView) findViewById(R.id.description);
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

        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" + printModel.trainNum + "次列车," +
                printModel.troubleStation + "站开车后验票，" +
                "在" + printModel.carriageNum + "车" + printModel.seatNum + "号座席发现一无票人员自述从" + printModel.zishuStartStation + "站上车，但拒绝补票，现移交你站，请按章办理。";

        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }

}
