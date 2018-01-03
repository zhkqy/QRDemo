package com.qr.demo.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import com.qr.demo.R;
import com.qr.demo.adapter.CommonModel;
import com.qr.demo.adapter.ContractNewCommonAdapter;
import com.qr.demo.common.CommonTextEditTextModel;
import com.qr.demo.db.DbHelper;
import com.qr.demo.dialog.CarriageAndSeatDialog;
import com.qr.demo.dialog.DateTimePickerDialog;
import com.qr.demo.dialog.ListViewDialog;
import com.qr.demo.model.PrintModel;
import com.qr.demo.previewactivity.LkywsPreMessageViewActivity;
import com.qr.demo.previewactivity.SjlcMessageViewActivity;
import com.qr.demo.utils.TimeUtils;

import java.util.Calendar;

/**
 * 石击列车电报
 */
public class SjlcMessageActivity extends NewBaseCommonActivity implements ContractNewCommonAdapter.CommonListener {

    CommonModel timeCommonModel;
    public Calendar currentCalendar;

    DateTimePickerDialog dialog;

    ListViewDialog listViewDialog;
    String strTitle;
    private CarriageAndSeatDialog carriageAndSeatDialog;

    @Override
    protected void initData() {
        super.initData();

        strTitle = getIntent().getStringExtra("title");
        currentCalendar = Calendar.getInstance();
        title.setText(strTitle);
        models.clear();

        String trainCode = DbHelper.getTrainNum(this);
        models.add(new CommonModel("列车车次", CommonModel.TYPE_TEXT_ARROW, false).
                setDescription(trainCode));

        String currentTime = TimeUtils.getCurrentTime();

        timeCommonModel = new CommonModel("当前日期", CommonModel.TYPE_TEXT_ARROW).
                setDescription(currentTime).setRequestCode(1101);

        models.add(timeCommonModel);

        models.add(new CommonModel("主送车站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));
        models.add(new CommonModel("事故车站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));
        models.add(new CommonModel(
                new CommonTextEditTextModel("事故记录", "", "请输入事故记录")));

        models.add(new CommonModel(
                new CommonTextEditTextModel("事故时间", "", "请输入事故时间")));
        models.add(new CommonModel("机后顺位", CommonModel.TYPE_LINE));
        models.add(new CommonModel(
                new CommonTextEditTextModel("机后顺位", "", "请输入机后顺位")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("机后顺位", "", "请输入机后顺位")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("机后顺位", "", "请输入机后顺位")));

        models.add(new CommonModel("破损玻璃", CommonModel.TYPE_LINE));
        models.add(new CommonModel(
                new CommonTextEditTextModel("玻璃块数", "", "请输入玻璃块数")));

        models.add(new CommonModel("受伤旅客", CommonModel.TYPE_LINE));
        models.add(new CommonModel(
                new CommonTextEditTextModel("旅客姓名", "", "请输入旅客姓名")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("身份证号", "", "请输入身份证号")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("原票票号", "", "请输入原票票号")));
        models.add(new CommonModel("原票发站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));
        models.add(new CommonModel("原票到站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));
        models.add(new CommonModel("车厢号　", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1106));

        models.add(new CommonModel("预览", CommonModel.TYPE_BUTTON).setRequestCode(1105));
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
                timeCommonModel.setDescription(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
                adapter.notifyDataSetChanged();
            }
        });
        dialog.show();
    }


    @Override
    public void onclick(final int position, CommonModel model) {

        if (model.getRequestCode() == 1101) {
            showDialog();
        } else if (model.getRequestCode() == 1102) {
            if (listViewDialog == null) {
                listViewDialog = new ListViewDialog(this, R.style.listDialog);
            }
            listViewDialog.setListener(null);
            listViewDialog.setListener(new ListViewDialog.Listener() {
                @Override
                public void onItemClicked(String str) {
                    adapter.getItem(position).setDescription(str);
                    adapter.notifyDataSetChanged();
                }
            });
            listViewDialog.show();
        } else if (model.getRequestCode() == 1105) {

            printModel.recordThing = strTitle;

            String time = adapter.getItem(1).getDescription();

            String[] str = time.split("-");

            if (str != null && str.length == 3) {
                printModel.year = str[0];
                printModel.month = str[1];
                printModel.day = str[2];
            }

            printModel.trainNum = adapter.getItem(0).getDescription();

            printModel.connectStation = adapter.getItem(2).getDescription();
            printModel.troubleStation = adapter.getItem(3).getDescription();
            printModel.troubleRecord = adapter.getItem(4).getEditTextModel().getEditTextStr();
            printModel.troubleMinute = adapter.getItem(5).getEditTextModel().getEditTextStr();


            printModel.jicheOne = adapter.getItem(7).getEditTextModel().getEditTextStr();
            printModel.jicheTwo = adapter.getItem(8).getEditTextModel().getEditTextStr();
            printModel.jicheThree = adapter.getItem(9).getEditTextModel().getEditTextStr();

            printModel.glassNum = adapter.getItem(11).getEditTextModel().getEditTextStr();

            printModel.name = adapter.getItem(13).getEditTextModel().getEditTextStr();
            printModel.cardNum = adapter.getItem(14).getEditTextModel().getEditTextStr();
            printModel.ticketNum = adapter.getItem(15).getEditTextModel().getEditTextStr();
            printModel.beginStation = adapter.getItem(16).getDescription();
            printModel.stopStation = adapter.getItem(17).getDescription();

            printModel.carriageNum = carriageNum;
            printModel.seatNum = seatNum;


            Intent mIntent = new Intent(this, SjlcMessageViewActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putBoolean("isEditStatus", isEditStatus);
            mBundle.putSerializable("data", printModel);
            mIntent.putExtras(mBundle);

            startActivity(mIntent);
        } else if (model.getRequestCode() == 1106) {
            if (carriageAndSeatDialog == null) {
                carriageAndSeatDialog = new CarriageAndSeatDialog(this, R.style.listDialog);
            }
            carriageAndSeatDialog.setListener(null);
            carriageAndSeatDialog.setListener(new CarriageAndSeatDialog.Listener() {
                @Override
                public void onItemClicked(String carriageNum, String seatNum) {

                    SjlcMessageActivity.this.carriageNum = carriageNum;
                    SjlcMessageActivity.this.seatNum = seatNum;

                    adapter.getItem(position).setDescription(carriageNum + "车" + seatNum + "号");
                    adapter.notifyDataSetChanged();
                }
            });
            carriageAndSeatDialog.show();
        }

    }
}