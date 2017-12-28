package com.qr.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qr.demo.R;

public class NewTitleView extends LinearLayout {

    Context mContext;
    private TextView discrption;

    public NewTitleView(Context context) {
        super(context);
        initView(context, null);
    }

    public NewTitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public NewTitleView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    TextView title;

    private void initView(Context context, AttributeSet attrs) {
        this.mContext = context;
        inflate(context, R.layout.new_view_title, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.title_view);

        String text = ta.getString(R.styleable.title_view_title);
        int arrowID = ta.getResourceId(R.styleable.title_view_arrow, 0);

        title = findViewById(R.id.title_name);
        ImageView arrow = findViewById(R.id.arrow);
        discrption = findViewById(R.id.discrption);

        title.setText(text);
        if (arrowID != 0) {
            arrow.setVisibility(View.VISIBLE);
            arrow.setImageResource(arrowID);
        } else {
            arrow.setVisibility(View.GONE);
        }
        ta.recycle();
    }

    public void setDiscrption(String str) {
        discrption.setText(str);
    }


    public void setTitle(String str) {
        title.setText(str);
    }

}
