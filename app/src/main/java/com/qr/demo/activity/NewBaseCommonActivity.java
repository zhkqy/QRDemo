package com.qr.demo.activity;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qr.demo.R;
import com.qr.demo.adapter.CommonModel;
import com.qr.demo.adapter.ContractNewCommonAdapter;
import com.qr.demo.event.KillBaseCommonList;
import com.qr.demo.model.PrintModel;
import com.qr.demo.view.NoScrollListViewForScrollView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class NewBaseCommonActivity extends BaseActivity {

    protected List<CommonModel> models = new ArrayList<>();

    protected NoScrollListViewForScrollView listview;
    protected ContractNewCommonAdapter adapter;
    protected FrameLayout bottomFramelayout;

    @BindView(R.id.tv_main_title)
    protected TextView title;

    public boolean isEditStatus = false;

    protected String carriageNum = "";
    protected String seatNum = "";
    protected String otherCarriageNum = "";  //other发站车厢号
    protected String otherSeatNum = "";  //other发站座位号

    PrintModel printModel = new PrintModel();

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_new_common);
        EventBus.getDefault().register(this);
        if (getIntent() != null) {
            isEditStatus = getIntent().getBooleanExtra("isEditStatus", false);
            PrintModel p = (PrintModel) getIntent().getSerializableExtra("data");
            if (p != null) {
                printModel = p;
                carriageNum = printModel.carriageNum;
                seatNum = printModel.seatNum;
                otherCarriageNum = printModel.otherCarriageNum;
                otherSeatNum = printModel.otherSeatNum;
            }
        }
        if (isEditStatus) {
            editData();
        } else {
            normalNoEditData();
        }

        bottomFramelayout = findViewById(R.id.bottom_framelayout);
        listview = findViewById(R.id.ll);
        adapter = new ContractNewCommonAdapter(this);
        listview.setAdapter(adapter);

    }

    protected abstract void normalNoEditData();

    protected abstract void editData();

//    protected   void normalNoEditData(){}
//
//    protected   void editData(){}


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


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void killSelf(KillBaseCommonList event) {
        finish();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }


}