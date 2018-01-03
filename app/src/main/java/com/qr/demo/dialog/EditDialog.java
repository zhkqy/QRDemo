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
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.qr.demo.R;
import com.qr.demo.activity.PrintActivity;
import com.qr.demo.db.DbHelper;
import com.qr.demo.model.CarriageNumModel;
import com.qr.demo.model.PrintModel;
import com.qr.demo.utils.DisplayUtil;
import com.qr.demo.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

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
