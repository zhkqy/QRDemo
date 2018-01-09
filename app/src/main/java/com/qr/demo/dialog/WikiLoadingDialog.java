package com.qr.demo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;

import com.qr.demo.R;

public class WikiLoadingDialog extends Dialog {
    private AnimationDrawable animationDrawable;

    private boolean mCancelAble;
    private Context mContext;


    public WikiLoadingDialog(Context context) {
        super(context);
        this.mContext = context;
        initView();
    }

    public WikiLoadingDialog(Context context, int theme) {
        super(context, theme);
        this.mContext = context;
        initView();
    }

    public WikiLoadingDialog(Context context, boolean cancelAble) {
        super(context);
        mCancelAble = cancelAble;
        this.mContext = context;
        initView();
    }


    private void initView() {
        View contentView = View.inflate(mContext, R.layout.dialog_wiki_loading, null);
        ImageView imageView = (ImageView) contentView.findViewById(R.id.iv_loading);
        animationDrawable = (AnimationDrawable) imageView.getBackground();
        this.setCancelable(mCancelAble);
        setContentView(contentView);
    }


    @Override
    public void dismiss() {
        if (animationDrawable != null) {
            animationDrawable.stop();
        }
        super.dismiss();
    }

    @Override
    public void show() {
        super.show();
        animationDrawable.start();
    }
}