package com.qr.demo.previewactivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qr.demo.R;
import com.qr.demo.model.PrintModel;
import com.qr.demo.view.CustomFontsTextView;

import butterknife.OnClick;

/**
 * Created by sun on 2017/12/29.
 */

public class YjyswpPreviewActivity extends BasePreviewActivity {

    String replaceStr1 = "拾到黑色行李箱一个，经会同乘警共同清点，内有现金xx元，现交你站按章处理 。";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_replace1);
        recordThing = (TextView) findViewById(R.id.recordThing);
        connectStation = (TextView) findViewById(R.id.connectStation);
        description = (CustomFontsTextView) findViewById(R.id.description);
        replace1 = (EditText) findViewById(R.id.replace1);
        isEditStatus = getIntent().getBooleanExtra("isEditStatus", false);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initUI() {

    }

    @Override
    protected void initListener() {
        save();
        replace1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                refreshDescription();
            }
        });
    }

    @Override
    protected void initData() {

        printModel = (PrintModel) getIntent().getSerializableExtra("data");
        recordThing.setText(printModel.recordThing);
        connectStation.setText(printModel.connectStation + "站:");
        if (isEditStatus) {
            replaceStr1 = printModel.replace1;
        }
        replace1.setText(replaceStr1);
        refreshDescription();
    }

    private void refreshDescription() {

        String discrep = null;
        try {
            discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" +
                    printModel.trainNum + "次列车终到" + printModel.troubleStation + "站后，列车员在" + printModel.chexiang + "座（铺）下," +
                    replace1.getText().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }


}
