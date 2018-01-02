package com.qr.demo.previewactivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
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
 */

public class YjjsPreviewActivity extends BaseActivity {

    PrintModel printModel;

    @BindView(R.id.recordThing)
    TextView recordThing;

    @BindView(R.id.connectStation)
    TextView connectStation;

    @BindView(R.id.replace1)
    EditText replace1;
    @BindView(R.id.replace2)
    EditText replace2;
    @BindView(R.id.replace3)
    EditText replace3;

    CustomFontsTextView description;
    private boolean isSending = false;
    private int interval;

    private String replaceStr1 = "硬座车厢一名";
    private String replaceStr2 = "上厕所关门时不慎将";
    private String replaceStr3 = "左手中指挤破流血";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_replace3);
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

        replace1.addTextChangedListener(new MyTextWatcher());
        replace2.addTextChangedListener(new MyTextWatcher());
        replace3.addTextChangedListener(new MyTextWatcher());
    }


    @Override
    protected void initData() {

        printModel = (PrintModel) getIntent().getSerializableExtra("data");

        replace1.setText(replaceStr1);
        replace2.setText(replaceStr2);
        replace3.setText(replaceStr3);

        recordThing.setText("记录事由:" + printModel.recordThing);
        connectStation.setText(printModel.connectStation + "站");

        refreshDescription();
    }

    private void refreshDescription() {

        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" + printModel.trainNum + "次列车，" + printModel.beforStation
                + "站到站前，" + printModel.carriageNum + "号" + replace1.getText().toString() + "旅客" + printModel.name + "，身份证号码" + printModel.cardNum + "，持" + printModel.beginStation +
                "站至" + printModel.stopStation + "站硬座车票，票号" + printModel.ticketNum + "，" + replace2.getText().toString() + "旅客" + printModel.otherName +
                "（身份证号码" + printModel.otherCardNum + "，持" + printModel.otherBeginStation + "站至" +
                printModel.otherStopStation + "站的硬座车票，票号" + printModel.otherTicketNum + ")的" +
                replace3.getText().toString() + "，列车进行了简单包扎处理，该旅客要求下车治疗，现移交你站，请按章办理。";


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


    class MyTextWatcher implements TextWatcher {


        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            refreshDescription();
        }
    }

}