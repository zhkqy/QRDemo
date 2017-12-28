package com.qr.demo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ListView;

public class NoScrollListViewForScrollView extends ListView {

    private boolean enabled;

    public NoScrollListViewForScrollView(Context context) {
        super(context);
    }

    public NoScrollListViewForScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollListViewForScrollView(Context context, AttributeSet attrs,
                                         int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    /**
     * 重写该方法，达到使ListView适应ScrollView的效果
     */
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        this.enabled = enabled;
    }

    public void setShowStatus(boolean enabled) {
        this.enabled = enabled;
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        if (enabled) {
            return true;
        } else {
            return super.onInterceptTouchEvent(ev);
        }

    }
}
