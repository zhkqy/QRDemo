package com.qr.demo.previewactivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.qr.demo.Label.YjgzlkLabel;
import com.qr.demo.MyApplication;
import com.qr.demo.R;
import com.qr.demo.activity.BaseActivity;
import com.qr.demo.model.PrintModel;
import com.qr.demo.view.CustomFontsTextView;
import com.qr.print.PrintPP_CPCL;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by sun on 2017/12/29.
 */

public class YjbmwtjsPreviewActivity extends BaseActivity {

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

    String replaceStr1 = "头部击伤，大约x公分的伤口，血流不止，列车进行了简单包扎处理，该旅客要求下车治疗";

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

        replace1.setText(replaceStr1);

        recordThing.setText("记录事由:" + printModel.recordThing);

        connectStation.setText(printModel.connectStation + "站");

        refreshDescription();
    }

    private void refreshDescription() {

        String discrep = "　　" + printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" +
                printModel.trainNum + "次列车将要运行" + printModel.connectStation + "站时，" + "运行方向左侧机次第" + printModel.carriageNum + "位车厢" +
                "，" + printModel.seatNum + "号座位处车窗外层玻璃被不明物体击碎，将" +
                "旅客" + printModel.name + ",身份证号码" + printModel.cardNum +
                "," + replace1.getText().toString() + ",现交你站，请按章办理";

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
                        YjgzlkLabel pl = new YjgzlkLabel(printPP_cpcl);

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
