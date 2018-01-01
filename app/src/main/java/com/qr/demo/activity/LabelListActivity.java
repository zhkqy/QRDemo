package com.qr.demo.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qr.demo.Constants;
import com.qr.demo.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/12/28.
 * <p>
 * 标签的列表
 */

public class LabelListActivity extends BaseActivity {


    @BindView(R.id.listview)
    ListView mListView;
    @BindView(R.id.tv_main_title)
    TextView mainTitle;


    LabelListAdapter labelListAdapter;

    private String type;

    @Override
    protected void setContentView() {

        setContentView(R.layout.activity_login);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initUI() {
        type = getIntent().getStringExtra("type");
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    protected void initData() {


        labelListAdapter = new LabelListAdapter(this);
        mListView.setAdapter(labelListAdapter);

        if ("passengerrecord".equals(type)) {
            mainTitle.setText("客户记录模板");
            labelListAdapter.setDatas(Constants.getPassengerRecordList());
        } else if ("traintelegram".equals(type)) {
            mainTitle.setText("列车电报模板");
            labelListAdapter.setDatas(Constants.getTrainTelegramList());
        }
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }

    class LabelListAdapter extends BaseAdapter {

        Context mContext;
        private ArrayList<String> datas;

        public LabelListAdapter(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = View.inflate(mContext, R.layout.item_label, null);
            TextView text = v.findViewById(R.id.text);
            text.setText(datas.get(position));

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    skip(datas.get(position));
                }
            });
            return v;
        }

        public void setDatas(ArrayList<String> datas) {
            this.datas = datas;
            notifyDataSetChanged();
        }
    }

    public void skip(String str) {

        if (str.equals("移交过站旅客")) {
            startActivity(new Intent(mContext, YjgzlkActivity.class).putExtra("title", "移交过站旅客"));
        } else if ("移交患病旅客".equals(str)) {
            startActivity(new Intent(mContext, YjhblkActivity.class).putExtra("title", "移交患病旅客"));
        } else if ("持挂失补车票中途下车到站退款".equals(str)) {
            startActivity(new Intent(mContext, CgspbcpztxcActivity.class).putExtra("title", "持挂失补车票中途下车到站退款"));
        } else if ("挂失补车票到站退款".equals(str)) {
            startActivity(new Intent(mContext, GsbcpdztkActivity.class).putExtra("title", "挂失补车票到站退款"));
        } else if ("移交未换纸质车票旅客".equals(str)) {
            startActivity(new Intent(mContext, YjwhzzcplkActivity.class).putExtra("title", "挂失补车票到站退款"));
        } else if ("".equals(str)) {

        }

    }


}
