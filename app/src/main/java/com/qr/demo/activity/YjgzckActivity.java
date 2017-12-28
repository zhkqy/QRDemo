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


    @Override
    protected void initData() {
        super.initData();

        currentCalendar = Calendar.getInstance();

        title.setText("移交过站旅客");
        models.clear();

        models.add(new CommonModel("列车车次", CommonModel.TYPE_TEXT_ARROW));

        models.add(new CommonModel("当前日期", CommonModel.TYPE_TEXT_ARROW));

//        models.add(new CommonModel("所在车厢", CommonModel.TYPE_TEXT_ARROW));

        models.add(new CommonModel(
                new CommonTextEditTextModel("乘客姓名", "", "请输入乘客姓名")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("身份证号", "", "请录入身份证号")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("乘客票号", "", "请录入票号")));


        adapter.setDatas(models);

        adapter.setListener(this);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private TextView currentTime;
    public static long currentDateLong = 0;
    public Calendar currentCalendar;


    public void showDialog() {
        DateTimePickerDialog dialog = new DateTimePickerDialog(this, currentCalendar.getTimeInMillis(), true);
        dialog.setOnDateTimeSetListener(new DateTimePickerDialog.OnDateTimeSetListener() {
            public void OnDateTimeSet(AlertDialog dialog, long date, Calendar calendar) {

                currentTime.setText(calendar.get(Calendar.YEAR) + "年" + (calendar.get(Calendar.MONTH) + 1) + "月" + calendar.get(Calendar.DAY_OF_MONTH) + "日");
                currentDateLong = TimeUtils.calendarConvertLong(calendar);
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