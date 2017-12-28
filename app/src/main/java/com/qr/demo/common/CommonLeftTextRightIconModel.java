package com.qr.demo.common;

/**
 * Created by chenlei on 2017/6/21.
 */
public class CommonLeftTextRightIconModel {

    public String title = "";
    public int rightIcon = 0;
    public int textColor = 0;

    public RightIconClickLinstener rightIconlistener;

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


    public RightIconClickLinstener getRightIconlistener() {
        return rightIconlistener;
    }

    public CommonLeftTextRightIconModel setRightIconlistener(RightIconClickLinstener rightIconlistener) {
        this.rightIconlistener = rightIconlistener;
        return this;
    }

    public CommonLeftTextRightIconModel setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getRightIcon() {
        return rightIcon;
    }

    public void setRightIcon(int rightIcon) {
        this.rightIcon = rightIcon;
    }

    public interface RightIconClickLinstener {

        void onclicked();
    }

}
