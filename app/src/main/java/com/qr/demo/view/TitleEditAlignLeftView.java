package com.qr.demo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qr.demo.R;

public class TitleEditAlignLeftView extends LinearLayout implements View.OnClickListener {

    Context mContext;
    private ExtendedEditText editText;
    TextView title;

    public TitleEditAlignLeftView(Context context) {
        super(context);
        initView(context, null);
    }

    public TitleEditAlignLeftView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context, attrs);
    }

    public TitleEditAlignLeftView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        this.mContext = context;
        inflate(context, R.layout.view_title_edit_align_left, this);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.title_view_edit);

        String text = ta.getString(R.styleable.title_view_edit_text);

        String hint = ta.getString(R.styleable.title_view_edit_hint);

        title = findViewById(R.id.title_name);

        editText = findViewById(R.id.edit);
        editText.setOnClickListener(this);
        editText.setCursorVisible(false);
        editText.setHint(hint);
        title.setText(text);
        ta.recycle();

    }

    @Override
    public void onClick(View view) {
        editText.setCursorVisible(true);
    }

    public void setTitle(String t) {
        title.setText(t);
    }

    /**
     * 实时返回输入框中的字符串监听
     *
     * @author ldkjjl
     */
    public interface GetEditTxtListener {
        public void callBackTxt(String str);
    }

    private GetEditTxtListener getEditTxtListener;


    public void setGetEditTxtListener(GetEditTxtListener getEditTxtListener) {
        this.getEditTxtListener = getEditTxtListener;

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (editText.hasFocus()) {
                    editText.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                        }

                        @Override
                        public void afterTextChanged(Editable editable) {
                            if (TitleEditAlignLeftView.this.getEditTxtListener != null) {
                                TitleEditAlignLeftView.this.getEditTxtListener.callBackTxt(editable.toString());
                            }
                        }
                    });
                } else {
                    editText.clearTextChangedListeners();//失去焦点时移除
                }
            }
        });

    }

    /**
     * 为输入框设置字符串
     */
    public void setText(String value) {
        editText.setText(value);
    }


    public void setHint(String value) {
        editText.setText("");
        editText.setHint(value);
    }

    /**
     * 直接返回字符串
     *
     * @return
     */
    public String getText() {
        return editText.getText().toString();
    }

    public ExtendedEditText getEditText() {
        return editText;
    }
}
