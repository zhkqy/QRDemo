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

        setContentView(R.layout.activity_label_list);
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
            mainTitle.setText("客运记录模板");
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
            TextView text = (TextView) v.findViewById(R.id.text);
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

        if ("移交过站旅客".equals(str)) {
            startActivity(new Intent(mContext, YjgzlkActivity.class).putExtra("title", str));
        } else if ("移交患病旅客".equals(str)) {
            startActivity(new Intent(mContext, YjhblkActivity.class).putExtra("title", str));
        } else if ("持挂失补车票中途下车到站退款".equals(str)) {
            startActivity(new Intent(mContext, CgspbcpztxcActivity.class).putExtra("title", str));
        } else if ("挂失补车票到站退款".equals(str)) {
            startActivity(new Intent(mContext, GsbcpdztkActivity.class).putExtra("title", str));
        } else if ("移交未换纸质车票旅客".equals(str)) {
            startActivity(new Intent(mContext, YjwhzzcplkActivity.class).putExtra("title", str));
        } else if ("移交无票人员".equals(str)) {
            startActivity(new Intent(mContext, YjwpryActivity.class).putExtra("title", str));
        } else if ("移交列车晚点中转旅客".equals(str)) {
            startActivity(new Intent(mContext, YjlcwdzzActivity.class).putExtra("title", str));
        } else if ("移交误乘旅客".equals(str)) {
            startActivity(new Intent(mContext, YjwcActivity.class).putExtra("title", str));
        } else if ("丢失车票补票后又找到原票到站退票".equals(str)) {
            startActivity(new Intent(mContext, DscpbphActivity.class).putExtra("title", str));
        } else if ("移交精神异常旅客".equals(str)) {
            startActivity(new Intent(mContext, YjjsycActivity.class).putExtra("title", str));
        } else if ("移交遗失物品".equals(str)) {
            startActivity(new Intent(mContext, YjyswpActivity.class).putExtra("title", str));
        } else if ("移交危险品".equals(str)) {
            startActivity(new Intent(mContext, YjwxpActivity.class).putExtra("title", str));
        } else if ("误售、误购到站退票".equals(str)) {
            startActivity(new Intent(mContext, WswgdzActivity.class).putExtra("title", str));
        } else if ("车辆故障到站退款".equals(str)) {
            startActivity(new Intent(mContext, ClgzdztkActivity.class).putExtra("title", str));
        } else if ("移交烫伤旅客".equals(str)) {
            startActivity(new Intent(mContext, YjtslkActivity.class).putExtra("title", str));
        } else if ("移交不明物体击伤旅客".equals(str)) {
            startActivity(new Intent(mContext, YjbmwtjsActivity.class).putExtra("title", str));
        } else if ("移交砸伤旅客".equals(str)) {
            startActivity(new Intent(mContext, YjzsActivity.class).putExtra("title", str));
        } else if ("移交挤手旅客".equals(str)) {
            startActivity(new Intent(mContext, YjjsActivity.class).putExtra("title", str));
        } else if ("超员电报".equals(str)) {
            startActivity(new Intent(mContext, CyMessageActivity.class).putExtra("title", str));
        } else if ("旅客意外伤电报".equals(str)) {
            startActivity(new Intent(mContext, LkywsMessageActivity.class).putExtra("title", str));
        } else if ("石击列车电报".equals(str)) {
            startActivity(new Intent(mContext, SjlcMessageActivity.class).putExtra("title", str));
        } else if ("移交砸伤旅客(无第三者责任)".equals(str)) {
            startActivity(new Intent(mContext, YjzsNoThreeActivity.class).putExtra("title", str));
        } else if ("移交挤手旅客(无第三者责任)".equals(str)) {
            startActivity(new Intent(mContext, YjjsNoThreeActivity.class).putExtra("title", str));
        } else if ("移交烫伤旅客(无第三者责任)".equals(str)) {
            startActivity(new Intent(mContext, SjlcMessageActivity.class).putExtra("title", str));
        }


    }

}
