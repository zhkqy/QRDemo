package com.qr.demo.common;

/**
 * Created by chenlei on 2017/6/21.
 */
public class CommonLeftTextRightIconModel {

    public String title = "";
    public int rightIcon = 0;
    public int textColor = 0;


    public CommonLeftTextRightIconModel(String title, int rightIcon) {
        this.title = title;
        this.rightIcon = rightIcon;
    }

    public String getTitle() {
        return title;
    }


    public int getTextColor() {
        return textColor;
    }


    public CommonLeftTextRightIconModel setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

}
