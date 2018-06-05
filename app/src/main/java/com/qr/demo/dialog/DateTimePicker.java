package com.qr.demo.dialog;

import android.content.Context;
import android.view.View;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.NumberPicker;
import android.widget.NumberPicker.OnValueChangeListener;

import com.qr.demo.R;

import java.util.Calendar;

public class DateTimePicker extends FrameLayout {
    private final NumberPicker mYearSpinner;
    private final NumberPicker mMonthSpinner;
    private final NumberPicker mDaySpinner;
    private Calendar mDate;
    private int mMonth, mDay;
    private String[] mDateDisplayValues = new String[7];
    private OnDateTimeChangedListener mOnDateTimeChangedListener;
    private boolean isShowDay = true;

    public DateTimePicker(Context context, boolean isShowDay) {
        super(context);
        this.isShowDay = isShowDay;
        mDate = Calendar.getInstance();
        mMonth = mDate.get(Calendar.MONTH) + 1;
        mDay = mDate.get(Calendar.DAY_OF_MONTH);
        inflate(context, R.layout.dialog_date, this);

        mYearSpinner = (NumberPicker) this.findViewById(R.id.np_year);
        mYearSpinner.setMinValue(0);
        mYearSpinner.setMaxValue(6);
        updateYearControl();
        mYearSpinner.setOnValueChangedListener(mOnDateChangedListener);

        mMonthSpinner = (NumberPicker) this.findViewById(R.id.np_month);
        mMonthSpinner.setMaxValue(12);
        mMonthSpinner.setMinValue(1);
        mMonthSpinner.setValue(mMonth);
        mMonthSpinner.setOnValueChangedListener(mOnHourChangedListener);

        mDaySpinner = (NumberPicker) this.findViewById(R.id.np_day);
        mDaySpinner.setMaxValue(31);
        mDaySpinner.setMinValue(1);
        mDaySpinner.setValue(mDay);
        mDaySpinner.setOnValueChangedListener(mOnMinuteChangedListener);


        mYearSpinner.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
        mMonthSpinner.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);
        mDaySpinner.setDescendantFocusability(DatePicker.FOCUS_BLOCK_DESCENDANTS);

        if(isShowDay){
            mDaySpinner.setVisibility(View.VISIBLE);
        }else{
            mDaySpinner.setVisibility(View.GONE);
        }
    }


    private OnValueChangeListener mOnDateChangedListener = new OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mDate.add(Calendar.YEAR, newVal - oldVal);
            updateYearControl();
            onDateTimeChanged();
        }
    };

    private OnValueChangeListener mOnHourChangedListener = new OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mMonth = mMonthSpinner.getValue() - 1;
            mDate.add(Calendar.MONTH, newVal - oldVal);
            int day = mDate.getActualMaximum(Calendar.DAY_OF_MONTH);
            mDaySpinner.setMaxValue(day);
            if (day < mDay) {
                mDaySpinner.setValue(day);
                mDaySpinner.invalidate();
            }
            onDateTimeChanged();
        }
    };

    private OnValueChangeListener mOnMinuteChangedListener = new OnValueChangeListener() {
        @Override
        public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
            mDate.add(Calendar.DAY_OF_MONTH, newVal - oldVal);
            onDateTimeChanged();
        }
    };

    private void updateYearControl() {
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(mDate.getTimeInMillis());
        int year = cal.get(Calendar.YEAR);
        mYearSpinner.setDisplayedValues(null);
        year = year - (7 / 2);
        for (int i = 0; i < 7; ++i) {
            mDateDisplayValues[i] = year + i + "";
        }
        mYearSpinner.setDisplayedValues(mDateDisplayValues);
        mYearSpinner.setValue(7 / 2);
        mYearSpinner.invalidate();
    }

    public interface OnDateTimeChangedListener {
        void onDateTimeChanged(DateTimePicker view, int year, int month, int day);
    }

    public void setOnDateTimeChangedListener(OnDateTimeChangedListener callback) {
        mOnDateTimeChangedListener = callback;

    }

    private void onDateTimeChanged() {
        if (mOnDateTimeChangedListener != null) {
            mOnDateTimeChangedListener.onDateTimeChanged(this, mDate.get(Calendar.YEAR),
                    mDate.get(Calendar.MONTH), mDate.get(Calendar.DAY_OF_MONTH));
        }
    }
}
