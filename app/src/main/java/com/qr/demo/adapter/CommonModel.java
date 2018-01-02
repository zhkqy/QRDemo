package com.qr.demo.adapter;

import com.qr.demo.common.CommonCheckBoxModel;
import com.qr.demo.common.CommonLeftTextRightIconModel;
import com.qr.demo.common.CommonLineModel;
import com.qr.demo.common.CommonTextEditTextModel;
import com.qr.demo.common.CommonToggleModel;

public class CommonModel {
    public String type = "";  //自己定义的type 展示listview不同的item
    public String title = ""; // 左边的title
    private String discrption = "";
    public String dataType = "";
    public boolean showArrow = true;
    public int requestCode = 0;  //请求码

    public static String TYPE_LINE = "type_line";    //显示一线
    public static String TYPE_TEXT_ARROW = "type_text_arrow";  //带箭头点击模式
    public static String TYPE_TEXT_EDITTEXT = "type_text_edittext";  //编辑模式
    public static String TYPE_LEFT_TEXT_RIGHT_ICON = "type_left_text_right_icon";  //左面text 右面icon的样式
    public static String TYPE_BUTTON = "type_button";  //按钮模式   例： 保存按钮

    public CommonLineModel lineModel;
    public CommonToggleModel toggleModel = new CommonToggleModel();
    public CommonTextEditTextModel editTextModel = new CommonTextEditTextModel("", "", "");
    public CommonLeftTextRightIconModel leftTextRightIconModel = new CommonLeftTextRightIconModel("", 0);
    public CommonCheckBoxModel checkBoxModel = new CommonCheckBoxModel();


    public CommonModel(String title, String type) {
        this.title = title;
        this.type = type;

    }

    public CommonModel(String title, String type, boolean showArrow) {
        this.title = title;
        this.type = type;
        this.showArrow = showArrow;
    }


    public CommonModel(String title, String editTextHide, String type) {
        if (type.equals(TYPE_TEXT_EDITTEXT)) {
            editTextModel = new CommonTextEditTextModel(title, "", editTextHide);
        }
    }

    public CommonModel(CommonTextEditTextModel model) {
        this(model.title, TYPE_TEXT_EDITTEXT);
        editTextModel = model;
    }


    public CommonModel setRequestCode(int requestCode) {
        this.requestCode = requestCode;
        return this;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public String getDiscrption() {
        return discrption;
    }

    public CommonModel setDiscrption(String discrption) {
        this.discrption = discrption;
        return this;
    }

    public CommonToggleModel getToggleModel() {
        return toggleModel;
    }


    public CommonCheckBoxModel getCheckBoxModel() {
        return checkBoxModel;
    }

    public void setCheckBoxModel(CommonCheckBoxModel checkBoxModel) {
        this.checkBoxModel = checkBoxModel;
    }

    public void setToggleModel(CommonToggleModel toggleModel) {
        this.toggleModel = toggleModel;
    }

    public CommonLeftTextRightIconModel getLeftTextRightIconModel() {
        return leftTextRightIconModel;
    }

    public void setLeftTextRightIconModel(CommonLeftTextRightIconModel leftTextRightIconModel) {
        this.leftTextRightIconModel = leftTextRightIconModel;
    }

    public CommonTextEditTextModel getEditTextModel() {
        return editTextModel;
    }

    public void setEditTextModel(CommonTextEditTextModel editTextModel) {
        this.editTextModel = editTextModel;
    }

    public CommonLineModel getLineModel() {
        return lineModel;
    }

    public void setLineModel(CommonLineModel lineModel) {
        this.lineModel = lineModel;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public CommonModel setDataType(String dataType) {
        this.dataType = dataType;
        return this;
    }

}
