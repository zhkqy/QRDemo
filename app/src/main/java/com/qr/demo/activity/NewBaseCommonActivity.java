package com.qr.demo.activity;

import android.widget.FrameLayout;
import android.widget.TextView;

import com.qr.demo.R;
import com.qr.demo.adapter.CommonModel;
import com.qr.demo.adapter.ContractNewCommonAdapter;
import com.qr.demo.view.NoScrollListViewForScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class NewBaseCommonActivity extends BaseActivity {

    protected List<CommonModel> models = new ArrayList<>();

    protected NoScrollListViewForScrollView listview;
    protected ContractNewCommonAdapter adapter;
    protected FrameLayout bottomFramelayout;

    @BindView(R.id.title)
    protected TextView title;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_common);


        bottomFramelayout = findViewById(R.id.bottom_framelayout);
        listview = findViewById(R.id.ll);
        adapter = new ContractNewCommonAdapter(this);
        listview.setAdapter(adapter);

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