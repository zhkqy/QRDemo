package com.qr.demo.previewactivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.qr.demo.R;
import com.qr.demo.activity.BaseActivity;
import com.qr.demo.activity.PrintActivity;
import com.qr.demo.db.SaveHelper;
import com.qr.demo.model.PrintModel;
import com.qr.demo.utils.ToastUtils;
import com.qr.demo.utils.Utils;
import com.qr.demo.view.CustomFontsTextView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/12/29.
 */

public class ClgzdztkPreviewActivity extends BaseActivity {

    PrintModel printModel;

    @BindView(R.id.recordThing)
    TextView recordThing;

    @BindView(R.id.connectStation)
    TextView connectStation;

    @BindView(R.id.replace1)
    EditText replace1;
    @BindView(R.id.replace2)
    EditText replace2;

    CustomFontsTextView description;

    String replaceStr1 = "由于车轮严重擦伤已甩车";
    private String replaceStr2 = "列车无能力安排，该旅客乘硬座至到站";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_replace2);
        description = findViewById(R.id.description);
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

        replace1.setText(replaceStr1);
        replace2.setText(replaceStr2);

        recordThing.setText("记录事由:" + printModel.recordThing);

        connectStation.setText(printModel.connectStation + "站");

        refreshDescription();
    }

    private void refreshDescription() {

        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" +
                printModel.trainNum + "次列车运行至" + printModel.connectStation + "站，" + printModel.carriageNum + "车," + "定员" + printModel.limitNum + "人," + replace1.getText().toString() +
                ",旅客" + printModel.name + ",身份证号码" + printModel.cardNum + ",持" + printModel.beginStation + "站至" + printModel.stopStation + "站车票，" +
                "票号" + printModel.ticketNum + "," + replace2.getText().toString() + ",现交你站，请按章办理。";

        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }

    public void save() {
        View v = findViewById(R.id.save);
        if (v != null) {
            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    printModel.saveRecordThing = recordThing.getText().toString();
                    printModel.saveConnectStation = connectStation.getText().toString();
                    printModel.savedescription = description.getText().toString();

                    printModel.repalce1 = replace1.getText().toString();
                    printModel.repalce2 = replace2.getText().toString();
                    printModel.uuid = Utils.getMyUUID();
                    printModel.saveCreateTime = System.currentTimeMillis();
                    try {
                        Gson gson = new Gson();
                        String jsonStr = gson.toJson(printModel);
                        SaveHelper.insert(mContext, jsonStr, printModel.uuid);
                        ToastUtils.show(mContext, "数据保存成功");
                    } catch (Exception e) {
                        ToastUtils.show(mContext, "数据保存失败");
                    }

                    Intent mIntent = new Intent(mContext, PrintActivity.class);
                    Bundle mBundle = new Bundle();
                    mBundle.putSerializable("data", printModel);
                    mIntent.putExtras(mBundle);
                    startActivity(mIntent);
                    finish();
                }
            });
        }
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
