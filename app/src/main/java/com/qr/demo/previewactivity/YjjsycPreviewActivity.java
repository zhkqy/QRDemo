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

public class YjjsycPreviewActivity extends BasePreviewActivity {


    boolean tongxing = false;

    String replaceStr1 = "突发精神异常，危及他人安全";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_replace1);
        recordThing = (TextView) findViewById(R.id.recordThing);
        connectStation = (TextView) findViewById(R.id.connectStation);
        description = (CustomFontsTextView) findViewById(R.id.description);
        replace1 = (EditText) findViewById(R.id.replace1);
        isEditStatus = getIntent().getBooleanExtra("isEditStatus", false);
        tongxing = getIntent().getBooleanExtra("tongxing", false);
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

        if (tongxing) {
            printModel.attachContent = "附：同行人姓名：×、身份证号码：×、车次×、车票发站：×、车票到站：×、票号×。";
        } else {
            printModel.attachContent = "";
        }

        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" + printModel.trainNum + "次列车运行至" + printModel.troubleStation + "站间，" +
                "旅客" + printModel.name + ",身份证号码" + printModel.cardNum + "家住" + printModel.address + ",持" + printModel.beginStation + "站至" + printModel.stopStation + "站的车票，" +
                "票号" + printModel.ticketNum + "," + replace1.getText().toString() + "，现交你站，请按章办理。";

        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }

}
