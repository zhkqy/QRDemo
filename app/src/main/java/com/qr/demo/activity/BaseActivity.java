package com.qr.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;

import com.qr.demo.R;

import butterknife.ButterKnife;

public abstract class BaseActivity extends FragmentActivity {
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

}
