package com.qr.demo.activity;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qr.demo.R;
import com.qr.demo.db.SaveHelper;
import com.qr.demo.dialog.EditDialog;
import com.qr.demo.model.PrintModel;

import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/12/28.
 */

public class SaveListActivity extends BaseActivity implements EditDialog.Listener {


    @BindView(R.id.listview)
    ListView listview;

    List<PrintModel> printModels;
    Adapter adapter;

    EditDialog editDialog = null;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_save_list);

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

        adapter = new Adapter();
        listview.setAdapter(adapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshList();
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }


    public void refreshList() {
        try {
            printModels = SaveHelper.getPrintModelData(this);
            Collections.reverse(printModels);
            adapter.notifyDataSetChanged();
        } catch (Exception e) {

        }
    }

    @Override
    public void onRefreshList() {
        refreshList();
    }


    class Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return printModels == null ? 0 : printModels.size();
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
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder holder = null;

            if (convertView == null) {
                convertView = View.inflate(SaveListActivity.this, R.layout.item_save_list, null);
                holder = new ViewHolder();
                holder.title = (TextView) convertView.findViewById(R.id.title);
                holder.recordThing = (TextView) convertView.findViewById(R.id.recordThing);
                holder.one = (TextView) convertView.findViewById(R.id.one);
                holder.two = (TextView) convertView.findViewById(R.id.two);
                holder.three = (TextView) convertView.findViewById(R.id.three);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            holder.three.setVisibility(View.GONE);

            final PrintModel printModel = printModels.get(position);

            if (printModel.saveRecordThing.contains("电报")) {
                holder.three.setVisibility(View.VISIBLE);
                holder.title.setText("列车电报");
                holder.recordThing.setText(printModel.saveRecordThing);
                holder.one.setText(printModel.saveZhusongDianBao);
                holder.two.setText(printModel.saveChaosongDianBao);
                holder.three.setText(printModel.savedescription);
            } else {
                holder.title.setText("客运记录");
                holder.three.setVisibility(View.GONE);
                holder.recordThing.setText(printModel.saveRecordThing);
                holder.one.setText(printModel.saveConnectStation);
                holder.two.setText(printModel.savedescription);
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (editDialog == null) {
                        editDialog = new EditDialog(mContext, R.style.listDialog);
                        editDialog.setListener(SaveListActivity.this);
                    }
                    editDialog.setDatas(printModel);
                    editDialog.show();
                }
            });

            return convertView;
        }
    }


    class ViewHolder {

        TextView title;
        TextView recordThing;
        TextView one;
        TextView two;
        TextView three;

    }


}
