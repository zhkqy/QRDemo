package com.qr.demo.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class ImeUtils {
    //隐藏输入法=
    public static void hideSoftInputBox(View v,Context context) {
//        InputMethodManager imm = (InputMethodManager)
//                context.getSystemService(Context.INPUT_METHOD_SERVICE);
//        // 接受软键盘输入的编辑文本或其它视图
//        imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);

        InputMethodManager inputMgr = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMgr.hideSoftInputFromWindow(v.getWindowToken(), 0);
//        boolean isOpen=inputMgr.isActive(v);
//
//        if(isOpen){
//            inputMgr.toggleSoftInput(InputMethodManager.HIDE_NOT_ALWAYS, 0);
//        }
    }

    //显示输入法
    public static void showSoftInputBox(Context context) {
        Activity activity = (Activity) context;
        if(activity != null){
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInputFromInputMethod(activity.getCurrentFocus().getWindowToken(), 0);
            imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
