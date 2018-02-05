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

public class ClgzdztkPreviewActivity extends BasePreviewActivity {

    String replaceStr1 = "由于车轮严重擦伤已甩车";
    private String replaceStr2 = "列车无能力安排，该旅客乘硬座至到站";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_replace2);
        description = (CustomFontsTextView) findViewById(R.id.description);
        connectStation = (TextView) findViewById(R.id.connectStation);
        recordThing = (TextView) findViewById(R.id.recordThing);
        replace1 = (EditText) findViewById(R.id.replace1);
        replace2 = (EditText) findViewById(R.id.replace2);
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
        replace1.addTextChangedListener(new MyTextWatcher());
        replace2.addTextChangedListener(new MyTextWatcher());
    }

    @Override
    protected void initData() {

        printModel = (PrintModel) getIntent().getSerializableExtra("data");

        if (isEditStatus) {
            replaceStr1 = printModel.replace1;
            replaceStr2 = printModel.replace2;
        }
        replace1.setText(replaceStr1);
        replace2.setText(replaceStr2);

        recordThing.setText(printModel.recordThing);

        connectStation.setText(printModel.connectStation + "站:");

        refreshDescription();
    }

    private void refreshDescription() {

        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" +
                printModel.trainNum + "次列车运行至" + printModel.troubleStation + "站，" + printModel.chexiang + "," + "定员" + printModel.limitNum + "人," + replace1.getText().toString() +
                ",旅客" + printModel.name + ",身份证号码" + printModel.cardNum + ",持" + printModel.beginStation + "站至" + printModel.stopStation + "站车票，" +
                "票号" + printModel.ticketNum + "," + replace2.getText().toString() + ",现交你站，请按章办理。";

        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }


    class MyTextWatcher implements TextWatcher {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            refreshDescription();
        }
    }
}
