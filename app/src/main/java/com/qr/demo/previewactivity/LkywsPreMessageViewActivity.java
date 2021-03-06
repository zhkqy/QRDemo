package com.qr.demo.previewactivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qr.demo.R;
import com.qr.demo.activity.BaseActivity;
import com.qr.demo.model.PrintModel;
import com.qr.demo.view.CustomFontsTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/12/29.
 */

public class LkywsPreMessageViewActivity extends BasePreviewActivity {

    String replaceStr1 = "（相关局集团公司)客运处、xx车务段（事故处理站的所属单位），大连客运段";
    String replaceStr2 = "上厕所关门时不慎将";
    String replaceStr3 = "右手大拇指挤伤流血";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_lkyws_msg);
        type = (TextView) findViewById(R.id.type);
        zhusong = (TextView) findViewById(R.id.zhusong);
        chasong = (TextView) findViewById(R.id.chasong);
        replace1 = (EditText) findViewById(R.id.replace1);
        replace2 = (EditText) findViewById(R.id.replace2);
        replace3 = (EditText) findViewById(R.id.replace3);
        description = (CustomFontsTextView) findViewById(R.id.description);
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
        replace3.addTextChangedListener(new MyTextWatcher());
    }

    @Override
    protected void initData() {

        printModel = (PrintModel) getIntent().getSerializableExtra("data");

        if (isEditStatus) {
            replaceStr1 = printModel.replace1;
            replaceStr2 = printModel.replace2;
            replaceStr3 = printModel.replace3;
        }

        replace1.setText(replaceStr1);
        replace2.setText(replaceStr2);
        replace3.setText(replaceStr3);

        type.setText(printModel.recordThing);

        refreshDescription();


    }

    private void refreshDescription() {

        String zhusong = "主送：" + printModel.connectStation + "站（事故处理站）";

        String chaosong = "抄送:" + replace1.getText().toString();

        String discrep = "内容：" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" +
                printModel.trainNum + "次列车" + printModel.troubleStation + "站开车后，" + printModel.chexiang + "座旅客" + printModel.name +
                "（身份证号码" + printModel.cardNum + "，持" + printModel.beginStation + "站至" + printModel.stopStation + "站硬座车票，票号" + printModel.ticketNum + ")" +
                "，" + replace2.getText().toString() + printModel.otherName + "（身份证号码" + printModel.otherCardNum + "，" +
                "持" + printModel.otherBeginStation + "站至" + printModel.otherStopStation + "站硬座车票，票号" + printModel.otherTicketNum + ")" +
                "，" + replace3.getText().toString() + "，列车进了简单包扎处理，受伤旅客" + printModel.otherName + "要求下车治疗，列车已做" + printModel.troubleRecord + "号客运记录，" +
                "将两名旅客及旁证材料交" + printModel.connectStation + "站按章办理，特电声明。";
        description.setText(discrep);

        this.zhusong.setText(zhusong);
        this.chasong.setText(chaosong);
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
