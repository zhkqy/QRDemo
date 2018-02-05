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

public class YjzsPreviewNoThreeActivity extends BasePreviewActivity {

    String replaceStr1 = "在拿取行李时不慎从行李架上滑落，砸中头部左后部，外观无伤痕，旅客自称头痛、恶心，要求下车治疗，";

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
        if (isEditStatus) {
            replaceStr1 = printModel.replace1;
        }
        replace1.setText(replaceStr1);
        recordThing.setText(printModel.recordThing);
        connectStation.setText(printModel.connectStation + "站:");

        refreshDescription();
    }

    private void refreshDescription() {
//
//
//        YjzsPreviewNoThreeActivity
//
//
//        记录事由:
//        移交砸伤旅客
//        xx站:
//        xx年x月x日，xx次列车运行至xx站至xx站间，x车xx座席旅客xxx，身份证号码xxxxxxx,
//                持xx站至xx站硬座车票，票号xxxxxx，在拿取行李时不慎从行李架上滑落，砸中头部左后部，
//        外观无伤痕，旅客自称头痛、恶心，要求下车治疗，现交你站，请按章办理。
//
//        附:
//        旁证材料两份


        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" +
                printModel.trainNum + "次列车运行至" + printModel.runBeginStation + "站至" + printModel.runStopStation + "站间,"
                + printModel.chexiang + "车" + "座席旅客" + printModel.name + "，身份证号码" + printModel.cardNum +
                ",持" + printModel.beginStation + "站至" + printModel.stopStation + "站硬座车票，" +
                "票号" + printModel.ticketNum + "," + replace1.getText().toString() + "要求下车治疗，现交你站，请按章办理。";


        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }


}
