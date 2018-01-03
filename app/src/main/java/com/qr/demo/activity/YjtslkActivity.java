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
import com.qr.demo.previewactivity.YjgzlkPreviewActivity;
import com.qr.demo.previewactivity.YjtslkPreviewActivity;
import com.qr.demo.utils.TimeUtils;

import java.util.Calendar;

/**
 * 移交烫伤旅客
 */
public class YjtslkActivity extends NewBaseCommonActivity implements ContractNewCommonAdapter.CommonListener {

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

        models.add(new CommonModel("交接车站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));

        models.add(new CommonModel("旅客A", CommonModel.TYPE_LINE));
        models.add(new CommonModel(
                new CommonTextEditTextModel("旅客姓名", "", "请输入旅客姓名")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("身份证号", "", "请输入身份证号")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("原票票号", "", "请输入原票票号")));

        models.add(new CommonModel("原票发站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));

        models.add(new CommonModel("原票到站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));
        models.add(new CommonModel("车厢号　", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1106));


        models.add(new CommonModel("旅客B", CommonModel.TYPE_LINE));

        models.add(new CommonModel(
                new CommonTextEditTextModel("旅客姓名", "", "请输入旅客姓名")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("旅客年龄", "", "请输入旅客年龄")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("旅客性别", "", "请输入旅客性别")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("原票票号", "", "请输入原票票号")));

        models.add(new CommonModel("原票发站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));

        models.add(new CommonModel("原票到站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));

        models.add(new CommonModel("车厢号　", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1107));

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
        } else if (model.getRequestCode() == 1102 ) {
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

            PrintModel printModel = new PrintModel();

            printModel.recordThing = strTitle;
            printModel.connectStation = adapter.getItem(2).getDescription();


            String time = adapter.getItem(1).getDescription();

            String[] str = time.split("-");

            if (str != null && str.length == 3) {
                printModel.year = str[0];
                printModel.month = str[1];
                printModel.day = str[2];
            }

            printModel.trainNum = adapter.getItem(0).getDescription();
            printModel.name = adapter.getItem(4).getEditTextModel().getEditTextStr();// 旅客名称
            printModel.cardNum = adapter.getItem(5).getEditTextModel().getEditTextStr();//  身份证号码
            printModel.ticketNum = adapter.getItem(6).getEditTextModel().getEditTextStr();// 票号
            printModel.beginStation = adapter.getItem(7).getDescription();// 旅客买的票 的开始位置
            printModel.stopStation = adapter.getItem(8).getDescription();// 旅客买的票 的结束位置
            printModel.carriageNum = carriageNum;
            printModel.seatNum = seatNum;

            printModel.otherName = adapter.getItem(11).getEditTextModel().getEditTextStr();  //other旅客姓名
            printModel.otherAge = adapter.getItem(12).getEditTextModel().getEditTextStr();  //other旅客年龄
            printModel.otherSex = adapter.getItem(13).getEditTextModel().getEditTextStr();  //other旅客性别
            printModel.otherTicketNum = adapter.getItem(14).getEditTextModel().getEditTextStr();  //other发站票号

            printModel.otherBeginStation = adapter.getItem(15).getDescription();  //other发站
            printModel.otherStopStation = adapter.getItem(16).getDescription();  //other到站
            printModel.otherCarriageNum = otherCarriageNum;  //other发站车厢号
            printModel.otherSeatNum = otherSeatNum;  //other发站座位号

            Intent mIntent = new Intent(this, YjtslkPreviewActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("data", printModel);
            mBundle.putBoolean("isEditStatus", isEditStatus);
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

                    YjtslkActivity.this.carriageNum = carriageNum;
                    YjtslkActivity.this.seatNum = seatNum;

                    adapter.getItem(position).setDescription(carriageNum + "车" + seatNum + "号");
                    adapter.notifyDataSetChanged();
                }
            });
            carriageAndSeatDialog.show();
        } else if (model.getRequestCode() == 1107) {

            if (carriageAndSeatDialog == null) {
                carriageAndSeatDialog = new CarriageAndSeatDialog(this, R.style.listDialog);
            }
            carriageAndSeatDialog.setListener(null);
            carriageAndSeatDialog.setListener(new CarriageAndSeatDialog.Listener() {
                @Override
                public void onItemClicked(String carriageNum, String seatNum) {

                    YjtslkActivity.this.otherCarriageNum = carriageNum;
                    YjtslkActivity.this.otherSeatNum = seatNum;

                    adapter.getItem(position).setDescription(carriageNum + "车" + seatNum + "号");
                    adapter.notifyDataSetChanged();
                }
            });
            carriageAndSeatDialog.show();
        }
    }
}