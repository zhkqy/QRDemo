package com.qr.demo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qr.demo.R;
import com.qr.demo.db.DbHelper;
import com.qr.demo.model.CarriageNumModel;
import com.qr.demo.utils.DisplayUtil;
import com.qr.demo.utils.ToastUtils;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class CarriageAndSeatDialog extends Dialog {

    private Context mContext;
    private Listener listener;

    private AdapterLeft adapterLeft;
    private AdapterRight adapterRight;

    private ListView leftListView;
    private ListView rightListView;

    private int leftPressPosition = 0;
    private int rightPressPosition = 0;

    private ArrayList<CarriageNumModel> carriageNumModels;


    public CarriageAndSeatDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        initView();
        initListView();
    }

    private void initView() {
        View contentView = View.inflate(mContext, R.layout.carriage_and_seat_dialog, null);
        leftListView = (ListView) contentView.findViewById(R.id.leftList);
        rightListView = (ListView) contentView.findViewById(R.id.rightList);
        setContentView(contentView);

        adapterLeft = new AdapterLeft(getContext());
        adapterRight = new AdapterRight(getContext());
    }

    private void initListView() {
        final ArrayList<String> leftList = new ArrayList<>();
        leftList.clear();

        carriageNumModels = DbHelper.getTrainCarriageNum(getContext());

        final TreeMap<String, String> map = new TreeMap<>();
        map.clear();
        //这里将数据加工
        if (carriageNumModels != null) {
            for (int x = 0; x < carriageNumModels.size(); x++) {
                CarriageNumModel carriageNumModel = carriageNumModels.get(x);
                map.put(carriageNumModel.carriageNum, "");
            }
        }

        for (Map.Entry<String, String> entry : map.entrySet()) {
            leftList.add(entry.getKey());
        }

        adapterLeft.setDatas(leftList);
        leftListView.setAdapter(adapterLeft);
        rightListView.setAdapter(adapterRight);

        leftListView.setOnItemClickListener(new LeftItemClicked());
        rightListView.setOnItemClickListener(new RightItemClicked());
        leftItemClicked(0);
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (!hasFocus) {
            return;
        }
        setHeight();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (adapterLeft.getCount() == 0) {
            ToastUtils.show(getContext(), "本地数据库关联不正确");
            dismiss();
        }
    }

    private void setHeight() {
        Window window = getWindow();
        DisplayMetrics displayMetrics = mContext.getResources().getDisplayMetrics();
        WindowManager.LayoutParams attributes = window.getAttributes();
        if (window.getDecorView().getHeight() >= (int) (displayMetrics.heightPixels * 0.7)) {
            attributes.height = (int) (displayMetrics.heightPixels * 0.7);
        }
        attributes.width = displayMetrics.widthPixels - DisplayUtil.dipToPixels(getContext(), 40);

        window.setAttributes(attributes);
    }


    public void setListener(Listener listener) {
        this.listener = listener;
    }

    class LeftItemClicked implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            leftItemClicked(position);
        }
    }


    public void leftItemClicked(int position) {
        leftPressPosition = position;
        rightPressPosition = 0;

        if (carriageNumModels != null && adapterLeft.getCount() > 0) {
            final ArrayList<String> rightList = new ArrayList<>();
            rightList.clear();
            String num = adapterLeft.getItem(leftPressPosition);

            for (int x = 0; x < carriageNumModels.size(); x++) {
                CarriageNumModel carriageNumModel = carriageNumModels.get(x);
                if (num.equals(carriageNumModel.carriageNum)) {
                    rightList.add(carriageNumModel.seatNum);
                }
            }
            adapterRight.setDatas(rightList);

            if (adapterRight.getCount() > 0) {
                rightListView.setSelection(0);
            }
        }

        adapterLeft.notifyDataSetChanged();

    }

    class RightItemClicked implements AdapterView.OnItemClickListener {


        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            rightPressPosition = position;
            adapterRight.notifyDataSetChanged();

            if (listener != null) {

                String carriageNum = adapterLeft.getItem(leftPressPosition);
                String seatNum = adapterRight.getItem(rightPressPosition);

                listener.onItemClicked(carriageNum, seatNum);
            }
            dismiss();
        }
    }


    public interface Listener {
        void onItemClicked(String carriageNum, String seatNum);
    }

    class AdapterRight extends BaseAdapter {

        Context mContext;
        private ArrayList<String> datas;

        public AdapterRight(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public String getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = View.inflate(mContext, R.layout.item_dialog_right, null);
            TextView text = (TextView) v.findViewById(R.id.text);
            text.setText(datas.get(position));

            if (rightPressPosition == position) {
                text.setBackgroundResource(R.color.gray_dd);
            } else {
                text.setBackgroundResource(R.color.white);
            }

            return v;
        }

        public void setDatas(ArrayList<String> datas) {
            this.datas = datas;
            notifyDataSetChanged();
        }
    }


    class AdapterLeft extends BaseAdapter {

        Context mContext;
        private ArrayList<String> datas;

        public AdapterLeft(Context mContext) {
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public String getItem(int position) {
            return datas.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            View v = View.inflate(mContext, R.layout.item_dialog_left, null);

            TextView text = (TextView) v.findViewById(R.id.text);

            if (leftPressPosition == position) {
                text.setBackgroundResource(R.color.gray_dd);
            } else {
                text.setBackgroundResource(R.color.white);
            }

            text.setText(datas.get(position));

            return v;
        }

        public void setDatas(ArrayList<String> datas) {
            this.datas = datas;
            notifyDataSetChanged();
        }
    }

}
