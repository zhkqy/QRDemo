package com.qr.demo.previewactivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qr.demo.Label.keYunRecordLabel;
import com.qr.demo.MyApplication;
import com.qr.demo.R;
import com.qr.demo.activity.BaseActivity;
import com.qr.demo.model.PrintModel;
import com.qr.demo.utils.MoneyUtils;
import com.qr.demo.view.CustomFontsTextView;
import com.qr.print.PrintPP_CPCL;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/12/29.
 */

public class YjyswpPreviewActivity extends BaseActivity {

    PrintModel printModel;

    @BindView(R.id.recordThing)
    TextView recordThing;

    @BindView(R.id.connectStation)
    TextView connectStation;

    @BindView(R.id.replace1)
    EditText replace1;

    CustomFontsTextView description;
    private boolean isSending = false;
    private int interval;

    String replaceStr1 = "拾到黑色行李箱一个，经会同乘警共同清点";

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_preview_replace1);
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
        recordThing.setText("记录事由:" + printModel.recordThing);
        connectStation.setText(printModel.connectStation + "站");
        replace1.setText(replaceStr1);
        refreshDescription();
    }

    private void refreshDescription() {

        String discrep = null;
        try {
            discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" +
                    printModel.trainNum + "次列车终到" + printModel.connectStation + "站后，列车员在" + printModel.carriageNum + "车" + printModel.seatNum + "号座（铺）下" +
                    replace1.getText().toString() + "，内有现金" + MoneyUtils.arabNumToChineseRMB(Double.parseDouble(printModel.money)) + "，现交你站按章处理 。";
        } catch (Exception e) {
            e.printStackTrace();
        }

        description.setText(discrep);
    }

    @OnClick(R.id.rl_title_bar_left)
    public void back(View v) {
        finish();
    }

    @OnClick(R.id.printOne)
    public void printOneOnclicked(View v) {
        final PrintPP_CPCL printPP_cpcl = ((MyApplication) getApplication()).getPrintPP_cpcl();
        if (!isSending) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    isSending = true;
                    if (((MyApplication) getApplication()).isConnected()) {
                        keYunRecordLabel pl = new keYunRecordLabel(printPP_cpcl);

                        pl.Lable(recordThing.getText().toString(), connectStation.getText().toString(),
                                description.getText().toString());
                    }
                    try {
                        interval = 0;
                        Thread.sleep(interval);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isSending = false;
                }
            }).start();
        }
    }


}
