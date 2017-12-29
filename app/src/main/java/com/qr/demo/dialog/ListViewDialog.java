package com.qr.demo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qr.demo.R;
import com.qr.demo.db.DbHelper;
import com.qr.demo.model.ZcStopTimeModel;

import java.util.ArrayList;

public class ListViewDialog extends Dialog {

    private Context mContext;
    private ListView mListView;
    private Adapter adapter;


    public ListViewDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        initView();
        initListView();
    }

    private void initView() {
        View contentView = View.inflate(mContext, R.layout.content_dialog, null);
        mListView = contentView.findViewById(R.id.lv);
        setContentView(contentView);

        adapter = new Adapter(getContext());
    }

    private void initListView() {
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<ZcStopTimeModel> zcStopTimeModels = DbHelper.getTrainList(getContext());

        if (zcStopTimeModels != null) {
            for (int x = 0; x < zcStopTimeModels.size(); x++) {
                arrayList.add(zcStopTimeModels.get(x).station_name);
            }
        }

        adapter.setDatas(arrayList);
        mListView.setAdapter(adapter);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            return;
        }
        setHeight();
    }

    private void setHeight() {
        Window window = getWindow();
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (window.getDecorView().getHeight() >= (int) (displayMetrics.heightPixels * 0.75)) {
            attributes.height = (int) (displayMetrics.heightPixels * 0.75);
        }
        window.setAttributes(attributes);
    }


    class Adapter extends BaseAdapter {

        Context mContext;
        private ArrayList<String> datas;

        public Adapter(Context mContext) {
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

            View v = View.inflate(mContext, R.layout.item_dialog_list_one, null);
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


    }

}
