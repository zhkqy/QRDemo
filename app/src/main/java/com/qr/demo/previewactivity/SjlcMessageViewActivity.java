package com.qr.demo.previewactivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qr.demo.R;
import com.qr.demo.activity.BaseActivity;
import com.qr.demo.model.PrintModel;
import com.qr.demo.view.CustomFontsTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/12/29.
 */

public class SjlcMessageViewActivity extends BasePreviewActivity {

    String replaceStr1 = "铁路总公司运输局，（相关局集团公司)客运处、安全监察室、车辆处、公安处，xx车务段（事故处理站所属单位）大连客运段";
    String replaceStr2 = "头部右前部受伤，伤口约x公分";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_sjlc_msg);
        type = (TextView) findViewById(R.id.type);
        zhusong = (TextView) findViewById(R.id.zhusong);
        chasong = (TextView) findViewById(R.id.chasong);
        replace1 = (EditText) findViewById(R.id.replace1);
        replace2 = (EditText) findViewById(R.id.replace2);
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

        type.setText(printModel.recordThing);


        refreshDescription();
    }

    private void refreshDescription() {

        String zhusong = "主送：" + printModel.connectStation + "站、" + printModel.connectStation + "站派出所（事故处理站）";

        String chaosong = "抄送:" + replace1.getText().toString();

        String discrep = "内容：" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" +
                printModel.trainNum + "次列车" + printModel.troubleStation + "站开车后" + printModel.troubleMinute + "分钟，列车机次第" + printModel.jicheOne + "位、" +
                "第" + printModel.jicheTwo + "位、第" + printModel.jicheThree + "位，车窗玻璃被不明物体连续击中，造成共" + printModel.glassNum + "块玻璃破损" +
                "及" + printModel.carriageNum + "车" + printModel.seatNum + "号座席处旅客" + printModel.name + "（身份证号码" + printModel.cardNum + "，" +
                "持" + printModel.beginStation + "站至" + printModel.stopStation + "站硬座车票，" +
                "票号" + printModel.ticketNum + ")" + replace2.getText().toString() + "，列车进行了简单包扎，该旅客要求下车治疗，" +
                "列车已做" + printModel.troubleRecord + "号记录，将受伤旅客及两份证明材料交" + printModel.connectStation + "站按章办理，特电声明。";
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
