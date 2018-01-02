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

public class YjzsPreviewActivity extends BaseActivity {

    PrintModel printModel;

    @BindView(R.id.recordThing)
    TextView recordThing;

    @BindView(R.id.connectStation)
    TextView connectStation;

    @BindView(R.id.replace1)
    EditText replace1;
    @BindView(R.id.replace2)
    EditText replace2;


    CustomFontsTextView description;
    private boolean isSending = false;
    private int interval;

    String replaceStr1 = "在拿取行李时不慎将行李从行李架上滑落，砸中";
    private String replaceStr2 = "头部左后部，外观无伤痕，被砸旅客自称头痛、恶心";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_clgzdztk);
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

        replace1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                refreshDescription();
            }
        });
        replace2.addTextChangedListener(new TextWatcher() {
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
        });
    }

    @Override
    protected void initData() {

        printModel = (PrintModel) getIntent().getSerializableExtra("data");

        replace1.setText(replaceStr1);
        replace2.setText(replaceStr2);

        recordThing.setText("记录事由:" + printModel.recordThing);

        connectStation.setText(printModel.connectStation + "站");

        refreshDescription();
    }

    private void refreshDescription() {

        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" +
                printModel.trainNum + "次列车运行至" + printModel.runBeginStation + "站至" + printModel.runStopStation + "站间,"
                + printModel.carriageNum + "车" + printModel.seatNum + "座席旅客" + printModel.name + "，身份证号码" + printModel.cardNum +
                ",持" + printModel.beginStation + "站至" + printModel.stopStation + "站硬座车票，" +
                "票号" + printModel.ticketNum + "," + replace1.getText().toString()
                + printModel.otherCarriageNum + "车" + printModel.otherSeatNum + "座席旅客" + printModel.otherName + "，(身份证号码" + printModel.othercardNum +
                ",持" + printModel.otherBeginStation + "站至" + printModel.otherStopStation + "站车票，" +
                "票号" + printModel.otherTicketNum + ")的" + replace2.getText().toString() + "，要求下车治疗，现交你站，请按章办理。";


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
