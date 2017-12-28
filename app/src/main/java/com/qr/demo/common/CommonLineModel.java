package com.qr.demo.common;

public class CommonLineModel {

    public int lineColor; //线的颜色
    public int lineHeight;//线的高度

    public CommonLineModel(int lineColor, int lineHeight) {
        this.lineColor = lineColor;
        this.lineHeight = lineHeight;
    }

    public int getLineColor() {
        return lineColor;
    }

    public void setLineColor(int lineColor) {
        this.lineColor = lineColor;
    }

    public int getLineHeight() {
        return lineHeight;
    }

    public void setLineHeight(int lineHeight) {
        this.lineHeight = lineHeight;
    }
}
