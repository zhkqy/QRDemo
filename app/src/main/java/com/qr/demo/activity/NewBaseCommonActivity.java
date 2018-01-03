package com.qr.demo.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.qr.demo.R;
import com.qr.demo.adapter.CommonModel;
import com.qr.demo.adapter.ContractNewCommonAdapter;
import com.qr.demo.model.PrintModel;
import com.qr.demo.view.NoScrollListViewForScrollView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class NewBaseCommonActivity extends BaseActivity {

    protected List<CommonModel> models = new ArrayList<>();

    protected NoScrollListViewForScrollView listview;
    protected ContractNewCommonAdapter adapter;
    protected FrameLayout bottomFramelayout;

    @BindView(R.id.tv_main_title)
    protected TextView title;

    public boolean isEditStatus = false;

    protected String carriageNum = "";
    protected String seatNum = "";
    protected String zhongzhuanCarriageNum = "";  //中转车厢号
    protected String zhongzhuanSeatNum = "";  //中转座位号
    protected String otherCarriageNum = "";  //other发站车厢号
    protected String otherSeatNum = "";  //other发站座位号

    PrintModel printModel = new PrintModel();

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_common);

        if (getIntent() != null) {
            isEditStatus = getIntent().getBooleanExtra("isEditStatus", false);
            PrintModel p = (PrintModel) getIntent().getSerializableExtra("data");
            if (p != null) {
                printModel = p;
            }
        }

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

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }

}