package com.qr.demo;

import android.app.Application;

import com.qr.print.PrintPP_CPCL;

/**
 * Created by sun on 2017/12/28.
 */

public class MyApplication extends Application {
    private boolean isConnected = false;
    private PrintPP_CPCL printPP_cpcl = null;
    private String connetName="";

    @Override
    public void onCreate() {
        super.onCreate();
        printPP_cpcl = new PrintPP_CPCL();
    }

    public PrintPP_CPCL getPrintPP_cpcl() {
        return printPP_cpcl;
    }

    public void setPrintPP_cpcl(PrintPP_CPCL printPP_cpcl) {
        this.printPP_cpcl = printPP_cpcl;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void setConnected(boolean connected) {
        isConnected = connected;
    }

    public void setConnetName(String connetName) {
        this.connetName = connetName;
    }

    public String getConnetName() {
        return connetName;
    }
}
