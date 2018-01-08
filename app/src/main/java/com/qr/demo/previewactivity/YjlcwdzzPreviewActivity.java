package com.qr.demo.previewactivity;

import android.view.View;
import android.widget.TextView;

import com.qr.demo.R;
import com.qr.demo.model.PrintModel;
import com.qr.demo.view.CustomFontsTextView;

import butterknife.OnClick;

/**
 * Created by sun on 2017/12/29.
 * 移交列车晚点中转旅客
 */

public class YjlcwdzzPreviewActivity extends BasePreviewActivity {


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

        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" + printModel.trainNum + "次列车到达" +
                printModel.connectStation + "站时晚点" + printModel.lateMinute + ",旅客" + printModel.name + ",身份证号码" + printModel.cardNum + "," +
                "持一张" + printModel.beginStation + "站至" + printModel.stopStation + "站车票," + printModel.carriageNum + "车" + printModel.seatNum + "号，" +
                "票号" + printModel.ticketNum + ",因列车晚点无法正常中转换乘" + printModel.zhongzTrainNum + "次列车," +
                "" + "持" + printModel.zhongzBeginStation + "车站至" + printModel.zhongzStopStation + "车站车票，" +
                printModel.zhongzCarriageNum + "车" + printModel.zhongzSeatNum + "车号席（铺）位，票号" + printModel.zhongzTicketNum + "，现移交你站，请按章办理。";

        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }

}
