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

public class YjssPreviewActivity extends BasePreviewActivity {

    String replaceStr1 = "上厕所时不慎与";
    private String replaceStr2 = "摔倒在地，左腿膝盖下疼痛难忍，无法行动。列车进行了简单救治，旅客要求下车治疗，现移交你站，请按章办理。";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_replace2);
        recordThing = (TextView) findViewById(R.id.recordThing);
        connectStation = (TextView) findViewById(R.id.connectStation);
        description = (CustomFontsTextView) findViewById(R.id.description);
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
        replace2.addTextChangedListener(new TextWatcher() {
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
        });
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
                printModel.trainNum + "次列车," + printModel.troubleStation + "站到站前,"
                + printModel.carriageNum + "车" + printModel.seatNum + "座席旅客" + printModel.name + "，身份证号码" + printModel.cardNum +
                ",持" + printModel.beginStation + "站至" + printModel.stopStation + "站硬座车票，" +
                "票号" + printModel.ticketNum + "," + replace1.getText().toString()
                + "旅客" + printModel.otherName + "（身份证号码" + printModel.otherCardNum + "，持" + printModel.otherBeginStation
                + "站至" + printModel.otherStopStation + "站的硬座车票，票号" + printModel.otherTicketNum + ")发生碰撞，造成旅客" + printModel.otherName +
                replace2.getText().toString();

        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }


}
