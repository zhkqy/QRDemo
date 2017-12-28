package com.qr.demo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.widget.Toast;

import butterknife.ButterKnife;

public  abstract class BaseActivity extends FragmentActivity {
    private long mExitTime;



    protected Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mContext = this;
        setContentView();
        ButterKnife.bind(this);

        initPresenter();
        initUI();
        initListener();
        initData();
    }


    protected abstract void setContentView();

    protected abstract void initPresenter();

    protected abstract void initUI();

    protected abstract void initListener();

    protected abstract void initData();


//
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//
//            if ((System.currentTimeMillis() - mExitTime) > 2000) {
//                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                mExitTime = System.currentTimeMillis();
//
//            } else {
//                finish();
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }


}
