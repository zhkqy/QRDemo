package com.qr.demo.activity;

import com.qr.demo.Label.YjgzlkLabel;
import com.qr.demo.R;

/**
 * Created by sun on 2017/12/28.
 */

public class PrintActivity extends BaseActivity {

    private boolean isSending = false;
    private int interval;

    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_print);

//        if (!isSending) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    isSending = true;
//                    if (isConnected) {
//                        YjgzlkLabel pl = new YjgzlkLabel();
//                        pl.Lable(PrintActivity.this, printPP_cpcl);
//                    }
//                    try {
//                        interval = 0;
//                        Thread.sleep(interval);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                    isSending = false;
//
//
//                }
//            }).start();
//        }


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

}
