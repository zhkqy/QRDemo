package com.qr.demo.common;

/**
 * Created by chenlei on 2017/6/21.
 */
public class CommonTextEditTextModel {
	
	public static final String INPUT_TYPE_NUM = "input_type_num";//输入类型为数字

    public String title = "";
    public String editTextHide = "";
    public String editTextStr = "";

    public int minLines;
    public String inputType = "";//输入类型,默认为文本


    public CommonTextEditTextModel(String title, String editTextStr, String editTextHide) {
        this.editTextHide = editTextHide;
        this.editTextStr = editTextStr;
        this.title = title;
    }

    public int getMinLines() {
        return minLines;
    }

    public CommonTextEditTextModel setMinLines(int minLines) {
        this.minLines = minLines;
        return this;
    }
    
    public CommonTextEditTextModel setInputType(String inputType){
    	this.inputType = inputType;
    	return this;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getEditTextHide() {
        return editTextHide;
    }

    public void setEditTextHide(String editTextHide) {
        this.editTextHide = editTextHide;
    }

    public String getEditTextStr() {
        return editTextStr;
    }

    public void setEditTextStr(String editTextStr) {
        this.editTextStr = editTextStr;
    }


}
