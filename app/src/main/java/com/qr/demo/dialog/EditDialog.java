package com.qr.demo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.qr.demo.R;
import com.qr.demo.activity.CgspbcpztxcActivity;
import com.qr.demo.activity.ClgzdztkActivity;
import com.qr.demo.activity.CyMessageActivity;
import com.qr.demo.activity.DscpbphActivity;
import com.qr.demo.activity.GsbcpdztkActivity;
import com.qr.demo.activity.LkywsMessageActivity;
import com.qr.demo.activity.PrintActivity;
import com.qr.demo.activity.SjlcMessageActivity;
import com.qr.demo.activity.WswgdzActivity;
import com.qr.demo.activity.YjbmwtjsActivity;
import com.qr.demo.activity.YjgzlkActivity;
import com.qr.demo.activity.YjhblkActivity;
import com.qr.demo.activity.YjjsActivity;
import com.qr.demo.activity.YjjsycActivity;
import com.qr.demo.activity.YjlcwdzzActivity;
import com.qr.demo.activity.YjtslkActivity;
import com.qr.demo.activity.YjwcActivity;
import com.qr.demo.activity.YjwhzzcplkActivity;
import com.qr.demo.activity.YjwpryActivity;
import com.qr.demo.activity.YjwxpActivity;
import com.qr.demo.activity.YjyswpActivity;
import com.qr.demo.activity.YjzsActivity;
import com.qr.demo.model.PrintModel;
import com.qr.demo.previewactivity.CyMessagePreViewActivity;
import com.qr.demo.utils.DisplayUtil;
import com.qr.demo.utils.SharedPreferencesUtil;

public class EditDialog extends Dialog implements View.OnClickListener {

    private Context mContext;
    private PrintModel datas;

    private View delete;
    private View edit;


    public EditDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        init(context);
    }

    private void init(Context context) {
        mContext = context;
        initView();
    }

    private void initView() {
        View contentView = View.inflate(mContext, R.layout.edit_dialog, null);
        delete = contentView.findViewById(R.id.delete);
        edit = contentView.findViewById(R.id.edit);
        contentView.findViewById(R.id.print).setOnClickListener(this);
        delete.setOnClickListener(this);
        edit.setOnClickListener(this);
        setContentView(contentView);

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
        if (window.getDecorView().getHeight() >= (int) (displayMetrics.heightPixels * 0.4)) {
            attributes.height = (int) (displayMetrics.heightPixels * 0.4);
        }
        attributes.width = displayMetrics.widthPixels - DisplayUtil.dipToPixels(getContext(), 80);

        window.setAttributes(attributes);
    }

    @Override
    public boolean onKeyShortcut(int keyCode, @NonNull KeyEvent event) {
        return super.onKeyShortcut(keyCode, event);
    }

    @Override
    protected void onStart() {
        super.onStart();

        String account = SharedPreferencesUtil.getInstance(getContext()).getString(SharedPreferencesUtil.ACCOUNT, "");
        if (!TextUtils.isEmpty(account)) {
            if (account.equals(SharedPreferencesUtil.SUPER_ACCOUNT)) {
                delete.setVisibility(View.VISIBLE);
                edit.setVisibility(View.VISIBLE);
            } else if (account.equals(SharedPreferencesUtil.NORMAL_ACCOUNT)) {
                delete.setVisibility(View.GONE);

                if (System.currentTimeMillis() - datas.saveCreateTime < (30 * 60 * 1000)) {
                    edit.setVisibility(View.VISIBLE);
                } else {
                    edit.setVisibility(View.GONE);
                    dismiss();
                }
            }
        }
    }

    public void setDatas(PrintModel datas) {
        this.datas = datas;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.edit:

                if ("移交过站旅客".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, YjgzlkActivity.class).putExtra("title", "移交过站旅客"));
                } else if ("移交患病旅客".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, YjhblkActivity.class).putExtra("title", "移交患病旅客"));
                } else if ("持挂失补车票中途下车到站退款".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, CgspbcpztxcActivity.class).putExtra("title", "持挂失补车票中途下车到站退款"));
                } else if ("挂失补车票到站退款".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, GsbcpdztkActivity.class).putExtra("title", "挂失补车票到站退款"));
                } else if ("移交未换纸质车票旅客".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, YjwhzzcplkActivity.class).putExtra("title", "移交未换纸质车票旅客"));
                } else if ("移交无票人员".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, YjwpryActivity.class).putExtra("title", "移交无票人员"));
                } else if ("移交列车晚点中转旅客".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, YjlcwdzzActivity.class).putExtra("title", "移交列车晚点中转旅客"));
                } else if ("移交误乘旅客".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, YjwcActivity.class).putExtra("title", "移交误乘旅客"));
                } else if ("丢失车票补票后又找到原票到站退票".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, DscpbphActivity.class).putExtra("title", "丢失车票补票后又找到原票到站退票"));
                } else if ("移交精神异常旅客".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, YjjsycActivity.class).putExtra("title", "移交精神异常旅客"));
                } else if ("移交遗失物品".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, YjyswpActivity.class).putExtra("title", "移交遗失物品"));
                } else if ("移交危险品".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, YjwxpActivity.class).putExtra("title", "移交危险品"));
                } else if ("误售、误购到站退票".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, WswgdzActivity.class).putExtra("title", "误售、误购到站退票"));
                } else if ("车辆故障到站退款".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, ClgzdztkActivity.class).putExtra("title", "车辆故障到站退款"));
                } else if ("移交烫伤旅客".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, YjtslkActivity.class).putExtra("title", "移交烫伤旅客"));
                } else if ("移交不明物体击伤旅客".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, YjbmwtjsActivity.class).putExtra("title", "移交不明物体击伤旅客"));
                } else if ("移交砸伤旅客".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, YjzsActivity.class).putExtra("title", "移交砸伤旅客"));
                } else if ("移交挤手旅客".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, YjjsActivity.class).putExtra("title", "移交挤手旅客"));
                } else if ("超员电报".equals(datas.recordThing)) {

                    Intent mIntent = new Intent(mContext, CyMessageActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("data", datas);
                    mBundle.putString("title", "超员电报");
                    mBundle.putBoolean("isEditStatus", true);
                    mIntent.putExtras(mBundle);
                    mContext.startActivity(mIntent);
                } else if ("旅客意外伤电报".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, LkywsMessageActivity.class).putExtra("title", "旅客意外伤电报"));
                } else if ("石击列车电报".equals(datas.recordThing)) {
                    mContext.startActivity(new Intent(mContext, SjlcMessageActivity.class).putExtra("title", "石击列车电报"));
                }

                dismiss();
                break;
            case R.id.delete:

                break;
            case R.id.print:

                Intent mIntent = new Intent(mContext, PrintActivity.class);
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("data", datas);
                mIntent.putExtras(mBundle);
                mContext.startActivity(mIntent);
                dismiss();
                break;
        }
    }
}