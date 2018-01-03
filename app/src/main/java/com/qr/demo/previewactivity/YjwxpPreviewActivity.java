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
 */

public class YjwxpPreviewActivity extends BasePreviewActivity {

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

        connectStation.setText(printModel.connectStation + "站（车站无派出所）:");

        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" + printModel.trainNum + "次列车," +
                printModel.connectStation + "站开车后，发现车厢连接处有一名旅客" + printModel.name + ",身份证号码" + printModel.cardNum + ",持"
                + printModel.beginStation + "站至" + printModel.stopStation + "站车票，" + "" +
                "票号" + printModel.ticketNum + ",携带" + printModel.goods + ",找到列车长，自称坐过站。现交你站，请按章处理。";

        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }

}
