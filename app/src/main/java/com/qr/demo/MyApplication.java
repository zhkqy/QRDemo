package com.qr.demo;

import android.app.Application;

import com.qr.print.PrintPP_CPCL;

/**
 * Created by sun on 2017/12/28.
 */

public class MyApplication extends Application{

    PrintPP_CPCL printPP_cpcl = new PrintPP_CPCL();

    public PrintPP_CPCL getPrintPP_cpcl() {
        return printPP_cpcl;
    }

    public void setPrintPP_cpcl(PrintPP_CPCL printPP_cpcl) {
        this.printPP_cpcl = printPP_cpcl;
    }
}
