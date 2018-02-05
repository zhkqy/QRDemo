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

public class YjssPreviewNoThreeActivity extends BasePreviewActivity {

    String replaceStr1 = " 在下铺过程中自己不慎踏空摔伤\\在车门口/上（下）车时不慎摔伤\\在出（入）厕所（通过台、过道）处不慎滑倒摔伤，自述××部位疼痛，列车已经组织人员对其进行救治，旅客要求XX站下车治疗，请贵站按章办理。";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_replace1);
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

        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" +
                printModel.trainNum + "次列车," + printModel.troubleStation + "站开车后,"
                + printModel.chexiang + "旅客（姓名" + printModel.name + "，身份证号码" + printModel.cardNum +
                ",持" + printModel.beginStation + "站至" + printModel.stopStation + "站硬座车票，" +
                "票号" + printModel.ticketNum + ")" + replace1.getText().toString();

        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }


}
