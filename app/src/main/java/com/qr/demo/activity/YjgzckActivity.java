package com.qr.demo.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.widget.TextView;

import com.qr.demo.adapter.CommonModel;
import com.qr.demo.adapter.ContractNewCommonAdapter;
import com.qr.demo.common.CommonTextEditTextModel;
import com.qr.demo.dialog.DateTimePickerDialog;
import com.qr.demo.utils.TimeUtils;

import java.util.Calendar;

public class YjgzckActivity extends NewBaseCommonActivity implements ContractNewCommonAdapter.CommonListener {

    CommonModel timeCommonModel;
    public Calendar currentCalendar;

    DateTimePickerDialog dialog;

    @Override
    protected void initData() {
        super.initData();

        String t = getIntent().getStringExtra("title");
        currentCalendar = Calendar.getInstance();
        title.setText(t);
        models.clear();

        models.add(new CommonModel("列车车次", CommonModel.TYPE_TEXT_ARROW, false).setDiscrption("ktest21"));

        String currentTime = TimeUtils.getCurrentTime();

        timeCommonModel = new CommonModel("当前日期", CommonModel.TYPE_TEXT_ARROW).setDiscrption(currentTime);

        models.add(timeCommonModel);

//        models.add(new CommonModel("所在车厢", CommonModel.TYPE_TEXT_ARROW));

        models.add(new CommonModel("开车车站", CommonModel.TYPE_TEXT_ARROW));

        models.add(new CommonModel(
                new CommonTextEditTextModel("旅客姓名", "", "请输入旅客姓名")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("身份证号", "", "请输入身份证号")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("乘客票号", "", "请输入票号")));

        models.add(new CommonModel("出发站　", CommonModel.TYPE_TEXT_ARROW));

        models.add(new CommonModel("到达站　", CommonModel.TYPE_TEXT_ARROW));

        adapter.setDatas(models);

        adapter.setListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void showDialog() {

        dialog = new DateTimePickerDialog(this, currentCalendar.getTimeInMillis(), true);
        dialog.setOnDateTimeSetListener(new DateTimePickerDialog.OnDateTimeSetListener() {
            public void OnDateTimeSet(AlertDialog dialog, long date, Calendar calendar) {
                timeCommonModel.setDiscrption(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
                adapter.notifyDataSetChanged();
            }
        });
        dialog.show();
    }


    @Override
    public void onclick(int position, CommonModel model) {

        if (model.title.equals("当前日期")) {
            showDialog();
        }

    }
}