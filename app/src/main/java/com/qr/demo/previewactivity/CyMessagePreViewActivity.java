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

import java.text.DecimalFormat;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/12/29.
 */

public class CyMessagePreViewActivity extends BaseActivity {

    PrintModel printModel;

    @BindView(R.id.type)
    TextView type;

    @BindView(R.id.zhusong)
    TextView zhusong;
    @BindView(R.id.chasong)
    TextView chasong;

    @BindView(R.id.replace1)
    EditText replace1;

    CustomFontsTextView description;

    String replaceStr1 = "（相关局集团公司)客运处，客调，大连客运段";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_cy_msg);
        description = findViewById(R.id.description);
    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected void initUI() {

        save();
    }

    @Override
    protected void initListener() {

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
        replace1.setText(replaceStr1);
        type.setText(printModel.recordThing);


        refreshDescription();
    }

    private void refreshDescription() {

        String chaoyuanbi = "";
        try {
            int limit = Integer.parseInt(printModel.limitNum);
            int have = Integer.parseInt(printModel.haveNum);
            DecimalFormat df = new DecimalFormat(".##");
            float d = (float) (have - limit) / (float) limit * 100f;
            chaoyuanbi = df.format(d);
        } catch (Exception e) {

        }

        String zhusong = "主送：" + printModel.trainNum + "次列车" + printModel.zhusongBeginStation + "站至" + printModel.zhusongStopStation + "站区间各停车站";
        String chaosong = "抄送：铁路总公司运输局客调，" + replace1.getText().toString();
        String discrep = "内容：" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" +
                "过" + printModel.chaoyuanStation + "站" + printModel.trainNum + "次列车超员严重，" + "硬座实际定员" + printModel.limitNum + "人" +
                "，车内现有人数" + printModel.haveNum + "人" + ",超员" + chaoyuanbi + "%,请各站严格控制票额，以确保列车安全正点";
        description.setText(discrep);

        this.zhusong.setText(zhusong);
        this.chasong.setText(chaosong);
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

                    printModel.saveRecordThing = type.getText().toString();
                    printModel.saveZhusongDianBao = zhusong.getText().toString();
                    printModel.saveChaosongDianBao = chasong.getText().toString();
                    printModel.savedescription = description.getText().toString();

                    printModel.repalce1 = replace1.getText().toString();
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
}
