package com.qr.demo.previewactivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import com.qr.demo.R;
import com.qr.demo.model.PrintModel;

import java.text.DecimalFormat;

import butterknife.OnClick;

/**
 * Created by sun on 2017/12/29.
 */

public class CyMessagePreViewActivity extends BasePreviewActivity {

    String replaceStr1 = "（相关局集团公司)客运处，客调，大连客运段";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_cy_msg);
        type = findViewById(R.id.type);
        description = findViewById(R.id.description);
        zhusong = findViewById(R.id.zhusong);
        chasong = findViewById(R.id.chasong);
        replace1 = findViewById(R.id.replace1);

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

}
