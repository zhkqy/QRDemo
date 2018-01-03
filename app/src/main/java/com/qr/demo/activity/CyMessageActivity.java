package com.qr.demo.activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import com.qr.demo.R;
import com.qr.demo.adapter.CommonModel;
import com.qr.demo.adapter.ContractNewCommonAdapter;
import com.qr.demo.common.CommonTextEditTextModel;
import com.qr.demo.db.DbHelper;
import com.qr.demo.dialog.DateTimePickerDialog;
import com.qr.demo.dialog.ListViewDialog;
import com.qr.demo.model.PrintModel;
import com.qr.demo.previewactivity.CyMessagePreViewActivity;
import com.qr.demo.utils.TimeUtils;

import java.util.Calendar;

/**
 * 超员电报
 */
public class CyMessageActivity extends NewBaseCommonActivity implements ContractNewCommonAdapter.CommonListener {

    CommonModel timeCommonModel;
    public Calendar currentCalendar;

    DateTimePickerDialog dialog;

    ListViewDialog listViewDialog;
    String strTitle;

    @Override
    protected void initData() {
        super.initData();
        strTitle = getIntent().getStringExtra("title");
        currentCalendar = Calendar.getInstance();
        title.setText(strTitle);
        models.clear();

        if (isEditStatus) {
            models.add(new CommonModel("列车车次", CommonModel.TYPE_TEXT_ARROW, false).
                    setDescription(printModel.trainNum));
            timeCommonModel = new CommonModel("当前日期", CommonModel.TYPE_TEXT_ARROW).
                    setDescription(printModel.year + "-" + printModel.month + "-" + printModel.day).setRequestCode(1101);
            models.add(timeCommonModel);
            models.add(new CommonModel(
                    new CommonTextEditTextModel("定员人数", printModel.limitNum, "定员人数")));
            models.add(new CommonModel(
                    new CommonTextEditTextModel("现有人数", printModel.haveNum, "现有人数")));
            models.add(new CommonModel("主送发站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102).setDescription(printModel.zhusongBeginStation));
            models.add(new CommonModel("主送到站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102).setDescription(printModel.zhusongStopStation));
            models.add(new CommonModel("超员车站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102).setDescription(printModel.chaoyuanStation));
            models.add(new CommonModel("预览", CommonModel.TYPE_BUTTON).setRequestCode(1105));

        } else {
            String trainCode = DbHelper.getTrainNum(this);
            models.add(new CommonModel("列车车次", CommonModel.TYPE_TEXT_ARROW, false).
                    setDescription(trainCode));
            String currentTime = TimeUtils.getCurrentTime();
            timeCommonModel = new CommonModel("当前日期", CommonModel.TYPE_TEXT_ARROW).
                    setDescription(currentTime).setRequestCode(1101);
            models.add(timeCommonModel);
            models.add(new CommonModel(
                    new CommonTextEditTextModel("定员人数", "", "定员人数")));
            models.add(new CommonModel(
                    new CommonTextEditTextModel("现有人数", "", "现有人数")));
            models.add(new CommonModel("主送发站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));
            models.add(new CommonModel("主送到站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));
            models.add(new CommonModel("超员车站", CommonModel.TYPE_TEXT_ARROW).setRequestCode(1102));
            models.add(new CommonModel("预览", CommonModel.TYPE_BUTTON).setRequestCode(1105));
        }

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
            printModel.limitNum = adapter.getItem(2).getEditTextModel().getEditTextStr();
            printModel.haveNum = adapter.getItem(3).getEditTextModel().getEditTextStr();

            printModel.zhusongBeginStation = adapter.getItem(4).getDescription();
            printModel.zhusongStopStation = adapter.getItem(5).getDescription();
            printModel.chaoyuanStation = adapter.getItem(6).getDescription();

            Intent mIntent = new Intent(this, CyMessagePreViewActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putSerializable("data", printModel);
            mIntent.putExtras(mBundle);

            startActivity(mIntent);
        }

    }
}