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

/**
 * Created by sun on 2017/12/28.
 * <p>
 * 标签的列表
 */

public class LabelListActivity extends BaseActivity {


    @BindView(R.id.listview)
    ListView mListView;

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
            labelListAdapter.setDatas(Constants.getPassengerRecordList());
        } else if ("traintelegram".equals(type)) {
            labelListAdapter.setDatas(Constants.getTrainTelegramList());
        }
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
                    startActivity(new Intent(mContext, YjgzckActivity.class).putExtra("title", datas.get(position)));
                }
            });
            return v;
        }

        public void setDatas(ArrayList<String> datas) {
            this.datas = datas;
            notifyDataSetChanged();
        }
    }


}
