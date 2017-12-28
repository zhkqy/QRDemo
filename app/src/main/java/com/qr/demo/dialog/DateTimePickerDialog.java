package com.qr.demo.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import java.util.Calendar;


public class DateTimePickerDialog extends AlertDialog implements DialogInterface.OnClickListener {
    private DateTimePicker mDateTimePicker;
    private Calendar mDate = Calendar.getInstance();
    private OnDateTimeSetListener mOnDateTimeSetListener;


    public DateTimePickerDialog(Context context, long date, boolean isShowDay) {
        super(context);
        mDateTimePicker = new DateTimePicker(context, isShowDay);
        setView(mDateTimePicker);
        mDateTimePicker.setOnDateTimeChangedListener(new DateTimePicker.OnDateTimeChangedListener() {
            @Override
            public void onDateTimeChanged(DateTimePicker view, int year, int month, int day) {
                mDate.set(Calendar.YEAR, year);
                mDate.set(Calendar.MONTH, month);
                mDate.set(Calendar.DAY_OF_MONTH, day);
            }
        });

        setButton("确定", this);
        setButton2("取消", (OnClickListener) null);
        mDate.setTimeInMillis(date);

    }


    public interface OnDateTimeSetListener {
        void OnDateTimeSet(AlertDialog dialog, long date, Calendar calendar);
    }

//    private void updateTitle(long date) {
//        int flag = DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_SHOW_TIME;
//        setTitle(DateUtils.formatDateTime(this.getContext(), date, flag));
//    }

    public void setOnDateTimeSetListener(OnDateTimeSetListener callBack) {
        mOnDateTimeSetListener = callBack;
    }

    public void onClick(DialogInterface arg0, int arg1) {
        if (mOnDateTimeSetListener != null) {
            mOnDateTimeSetListener.OnDateTimeSet(this, mDate.getTimeInMillis(), mDate);
        }
    }
}
