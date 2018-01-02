package com.qr.demo.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import com.qr.demo.R;
import com.qr.demo.adapter.CommonModel;
import com.qr.demo.adapter.ContractNewCommonAdapter;
import com.qr.demo.common.CommonTextEditTextModel;
import com.qr.demo.db.DbHelper;
import com.qr.demo.dialog.CarriageDialog;
import com.qr.demo.dialog.DateTimePickerDialog;
import com.qr.demo.dialog.ListViewDialog;
import com.qr.demo.model.PrintModel;
import com.qr.demo.previewactivity.CgspbcpztxcPreviewActivity;
import com.qr.demo.previewactivity.YjwhzzcplkPreviewActivity;
import com.qr.demo.utils.TimeUtils;

import java.util.Calendar;

/**
 * 移交未换纸质车票旅客
 */
public class YjwhzzcplkActivity extends NewBaseCommonActivity implements ContractNewCommonAdapter.CommonListener {

    private CommonModel timeCommonModel;
    private Calendar currentCalendar;
    private DateTimePickerDialog dialog;
    private ListViewDialog listViewDialog;
    private CarriageDialog carriageDialog;

    private String strTitle;


    @Override
    protected void initData() {
        super.initData();

        strTitle = getIntent().getStringExtra("title");
        currentCalendar = Calendar.getInstance();
        title.setText(strTitle);
        models.clear();

        String trainCode = DbHelper.getTrainNum(this);
        models.add(new CommonModel("列车车次", CommonModel.TYPE_TEXT_ARROW, false).
                setDiscrption(trainCode));

        String currentTime = TimeUtils.getCurrentTime();

        timeCommonModel = new CommonModel("当前日期", CommonModel.TYPE_TEXT_ARROW).
                setDiscrption(currentTime).setRequestCode(1101);

        models.add(timeCommonModel);

        models.add(new CommonModel("交接车站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));

        models.add(new CommonModel(
                new CommonTextEditTextModel("旅客姓名", "", "请输入旅客姓名")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("身份证号", "", "请输入身份证号")));

        models.add(new CommonModel(
                new CommonTextEditTextModel("订单号码", "", "请输入订单号码")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("自述因　", "", "请输入自述因")));

        models.add(new CommonModel("网购发站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1103));

        models.add(new CommonModel("网购到站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1104));
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
                timeCommonModel.setDiscrption(calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH));
                adapter.notifyDataSetChanged();
            }
        });
        dialog.show();
    }


    @Override
    public void onclick(final int position, CommonModel model) {

        if (model.getRequestCode() == 1101) {
            showDialog();
        } else if (model.getRequestCode() == 1102 ||
                model.getRequestCode() == 1103 ||
                model.getRequestCode() == 1104) {
            if (listViewDialog == null) {
                listViewDialog = new ListViewDialog(this, R.style.listDialog);
            }
            listViewDialog.setListener(null);
            listViewDialog.setListener(new ListViewDialog.Listener() {
                @Override
                public void onItemClicked(String str) {
                    adapter.getItem(position).setDiscrption(str);
                    adapter.notifyDataSetChanged();
                }
            });
            listViewDialog.show();
        } else if (model.getRequestCode() == 1105) {

            PrintModel printModel = new PrintModel();

            printModel.recordThing = strTitle;
            String time = adapter.getItem(1).getDiscrption();
            String[] str = time.split("-");

            if (str != null && str.length == 3) {
                printModel.year = str[0];
                printModel.month = str[1];
                printModel.day = str[2];
            }

            printModel.trainNum = adapter.getItem(0).getDiscrption();
            printModel.connectStation = adapter.getItem(2).getDiscrption();
            printModel.name = adapter.getItem(3).getEditTextModel().getEditTextStr();// 旅客名称
            printModel.cardNum = adapter.getItem(4).getEditTextModel().getEditTextStr();//  身份证号码
            printModel.netOrderNum = adapter.getItem(5).getEditTextModel().getEditTextStr();//网购订单号
            printModel.netErrorReason = adapter.getItem(6).getEditTextModel().getEditTextStr();//自述因
            printModel.netBeginStation = adapter.getItem(7).getDiscrption();// 旅客买的票 的开始位置
            printModel.netStopStation = adapter.getItem(8).getDiscrption();// 旅客买的票 的结束位置
            printModel.carriageNum = carriageNum;// 旅客买的票 的结束位置
            printModel.seatNum = seatNum;

            Intent mIntent = new Intent(this, YjwhzzcplkPreviewActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("data", printModel);
            mIntent.putExtras(mBundle);

            startActivity(mIntent);
        } else if (model.getRequestCode() == 1106) {
            if (carriageDialog == null) {
                carriageDialog = new CarriageDialog(this, R.style.listDialog);
            }
            carriageDialog.setListener(null);
            carriageDialog.setListener(new CarriageDialog.Listener() {
                @Override
                public void onItemClicked(String carriageNum, String seatNum) {

                    YjwhzzcplkActivity.this.carriageNum = carriageNum;
                    YjwhzzcplkActivity.this.seatNum = seatNum;

                    adapter.getItem(position).setDiscrption(carriageNum + "车" + seatNum + "号");
                    adapter.notifyDataSetChanged();
                }
            });
            carriageDialog.show();
        }

    }
}