package com.qr.demo.activity;

import android.view.View;

import com.qr.demo.Label.YjgzlkLabel;
import com.qr.demo.MyApplication;
import com.qr.demo.R;
import com.qr.demo.model.PrintModel;
import com.qr.print.PrintPP_CPCL;

import butterknife.OnClick;

/**
 * Created by sun on 2017/12/28.
 */

public class PrintActivity extends BaseActivity {

    private boolean isSending = false;
    private int interval;
    PrintPP_CPCL printPP_cpcl;
    private MyApplication myApplication;

    PrintModel printModel;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_print);
        myApplication = (MyApplication) getApplication();

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

    }

    @OnClick(R.id.printOne)
    public void printOneOnclicked(View v) {
        print();
    }

    @OnClick(R.id.printTwo)
    public void printTwoOnclicked(View v) {
        print();
    }

    private void print() {
        printPP_cpcl = ((MyApplication) getApplication()).getPrintPP_cpcl();
        if (!isSending) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    isSending = true;
                    if (myApplication.isConnected()) {
                        YjgzlkLabel pl = new YjgzlkLabel();
                        pl.Lable(printPP_cpcl, printModel);
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
