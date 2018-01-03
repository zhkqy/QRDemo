package com.qr.demo.activity;

import android.view.View;

import com.qr.demo.Label.DianBaoLabel;
import com.qr.demo.Label.keYunRecordLabel;
import com.qr.demo.MyApplication;
import com.qr.demo.R;
import com.qr.demo.model.PrintModel;
import com.qr.print.PrintPP_CPCL;

import butterknife.OnClick;

/**
 * Created by sun on 2018/1/3.
 */

public class PrintActivity extends BaseActivity {

    private boolean isSending = false;
    private int interval;
    private PrintModel printModel;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_print);
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
    }

    public void printOneOnclicked(View v) {
        final PrintPP_CPCL printPP_cpcl = ((MyApplication) getApplication()).getPrintPP_cpcl();
        if (!isSending) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    isSending = true;
                    if (((MyApplication) getApplication()).isConnected()) {
                        keYunRecordLabel pl = new keYunRecordLabel(printPP_cpcl);

//                        pl.Lable(recordThing.getText().toString(), connectStation.getText().toString(),
//                                description.getText().toString());

//                        DianBaoLabel pl = new DianBaoLabel(printPP_cpcl);
//                        pl.Lable(type.getText().toString(), zhusong.getText().toString(),
//                                chasong.getText().toString(), description.getText().toString());
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
