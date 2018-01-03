package com.qr.demo.previewactivity;

import android.view.View;
import android.widget.TextView;

import com.qr.demo.Label.YjgzlkLabel;
import com.qr.demo.MyApplication;
import com.qr.demo.R;
import com.qr.demo.activity.BaseActivity;
import com.qr.demo.model.PrintModel;
import com.qr.demo.view.CustomFontsTextView;
import com.qr.print.PrintPP_CPCL;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/12/29.
 * 移交列车晚点中转旅客
 */

public class YjlcwdzzPreviewActivity extends BaseActivity {

    PrintModel printModel;

    @BindView(R.id.recordThing)
    TextView recordThing;

    @BindView(R.id.connectStation)
    TextView connectStation;

    CustomFontsTextView description;
    private boolean isSending = false;
    private int interval;


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

    }

    @Override
    protected void initData() {

        printModel = (PrintModel) getIntent().getSerializableExtra("data");

        recordThing.setText("记录事由:" + printModel.recordThing);

        connectStation.setText(printModel.connectStation + "站");

        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" + printModel.trainNum + "次列车到达" +
                printModel.connectStation + "站时晚点" + printModel.lateMinute + ",旅客" + printModel.name + ",身份证号码" + printModel.cardNum + "," +
                "持一张" + printModel.beginStation + "站至" + printModel.stopStation + "站车票," + printModel.carriageNum + "车" + printModel.seatNum + "号，" +
                "票号" + printModel.ticketNum + ",因列车晚点无法正常中转换乘" + printModel.zhongzhuanTrainNum + "次列车," +
                "" + "持" + printModel.zhongzhuanBeginStation + "车站至" + printModel.zhongzhuanStopStation + "车站车票，" +
                printModel.zhongzhuanCarriageNum + "车" + printModel.zhongzhuanSeatNum + "车号席（铺）位，票号" + printModel.zhongzhuanTicketNum + "，现移交你站，请按章办理。";

        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }

    @OnClick(R.id.printOne)
    public void printOneOnclicked(View v) {
        final PrintPP_CPCL printPP_cpcl = ((MyApplication) getApplication()).getPrintPP_cpcl();
        if (!isSending) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    isSending = true;
                    if (((MyApplication) getApplication()).isConnected()) {
                        YjgzlkLabel pl = new YjgzlkLabel(printPP_cpcl);

                        pl.Lable(recordThing.getText().toString(), connectStation.getText().toString(),
                                description.getText().toString());
                    }
                    try {
                        interval = 0;
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isSending = false;
                }
            }).start();
        }
    }
}
