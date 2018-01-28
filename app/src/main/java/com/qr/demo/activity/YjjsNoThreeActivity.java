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
import com.qr.demo.previewactivity.YjjsPreviewActivity;
import com.qr.demo.previewactivity.YjjsPreviewNoThreeActivity;
import com.qr.demo.utils.TimeUtils;

import java.util.Calendar;

/**
 * 移交挤手旅客(无第三者责任)
 */
public class YjjsNoThreeActivity extends NewBaseCommonActivity implements ContractNewCommonAdapter.CommonListener {

    CommonModel timeCommonModel;
    public Calendar currentCalendar;

    DateTimePickerDialog dialog;

    ListViewDialog listViewDialog;
    String strTitle;
    private CarriageDialog carriageDialog;

    @Override
    protected void normalNoEditData() {
        models.clear();

        String trainCode = DbHelper.getTrainNum(this);
        models.add(new CommonModel("列车车次", CommonModel.TYPE_TEXT_ARROW, false).
                setDescription(trainCode));

        String currentTime = TimeUtils.getCurrentTime();

        timeCommonModel = new CommonModel("当前日期", CommonModel.TYPE_TEXT_ARROW).
                setDescription(currentTime).setRequestCode(1101);

        models.add(timeCommonModel);

        models.add(new CommonModel("交接车站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));
        models.add(new CommonModel("事故站点", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));

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

        models.add(new CommonModel("预览", CommonModel.TYPE_BUTTON).setRequestCode(1105));
    }

    @Override
    protected void editData() {
        models.clear();

        models.add(new CommonModel("列车车次", CommonModel.TYPE_TEXT_ARROW, false).
                setDescription(printModel.trainNum));
        timeCommonModel = new CommonModel("当前日期", CommonModel.TYPE_TEXT_ARROW).
                setDescription(printModel.year + "-" + printModel.month + "-" + printModel.day).setRequestCode(1101);

        models.add(timeCommonModel);

        models.add(new CommonModel("交接车站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102)
                .setDescription(printModel.connectStation));
        models.add(new CommonModel("事故站点", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102)
                .setDescription(printModel.troubleStation));

        models.add(new CommonModel("旅客A", CommonModel.TYPE_LINE));
        models.add(new CommonModel(
                new CommonTextEditTextModel("旅客姓名", printModel.name, "请输入旅客姓名")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("身份证号", printModel.cardNum, "请输入身份证号")));
        models.add(new CommonModel(
                new CommonTextEditTextModel("原票票号", printModel.ticketNum, "请输入原票票号")));
        models.add(new CommonModel("原票发站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102)
                .setDescription(printModel.beginStation));
        models.add(new CommonModel("原票到站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102)
                .setDescription(printModel.stopStation));
        models.add(new CommonModel("车厢号　", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1106)
                .setDescription(printModel.carriageNum + "车"));


        models.add(new CommonModel("预览", CommonModel.TYPE_BUTTON).setRequestCode(1105));
    }

    @Override
    protected void initData() {
        super.initData();

        strTitle = getIntent().getStringExtra("title");
        currentCalendar = Calendar.getInstance();
        title.setText(strTitle);
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
            printModel.connectStation = adapter.getItem(2).getDescription();


            String time = adapter.getItem(1).getDescription();

            String[] str = time.split("-");

            if (str != null && str.length == 3) {
                printModel.year = str[0];
                printModel.month = str[1];
                printModel.day = str[2];
            }

            printModel.trainNum = adapter.getItem(0).getDescription();

            printModel.troubleStation = adapter.getItem(3).getDescription();

            printModel.name = adapter.getItem(5).getEditTextModel().getEditTextStr();// 旅客名称
            printModel.cardNum = adapter.getItem(6).getEditTextModel().getEditTextStr();//  身份证号码
            printModel.ticketNum = adapter.getItem(7).getEditTextModel().getEditTextStr();// 票号
            printModel.beginStation = adapter.getItem(8).getDescription();// 旅客买的票 的开始位置
            printModel.stopStation = adapter.getItem(9).getDescription();// 旅客买的票 的结束位置

            printModel.carriageNum = carriageNum;

            Intent mIntent = new Intent(this, YjjsPreviewNoThreeActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("data", printModel);
            mBundle.putBoolean("isEditStatus", isEditStatus);
            mIntent.putExtras(mBundle);

            startActivity(mIntent);
        } else if (model.getRequestCode() == 1106) {

            if (carriageDialog == null) {
                carriageDialog = new CarriageDialog(this, R.style.listDialog);
            }
            carriageDialog.setListener(null);
            carriageDialog.setListener(new CarriageDialog.Listener() {
                @Override
                public void onItemClicked(String carriageNum) {
                    YjjsNoThreeActivity.this.carriageNum = carriageNum;
                    adapter.getItem(position).setDescription(carriageNum + "车");
                    adapter.notifyDataSetChanged();
                }
            });
            carriageDialog.show();
        }
    }
}