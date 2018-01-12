package com.qr.demo.Label;

import android.content.Context;

import com.qr.demo.model.PrintModel;
import com.qr.print.PrintPP_CPCL;

public class DianBaoLabel2 extends DianBaoBaseLabel {

    String recordThing;
    String zhusong;
    String chaosong;
    String description;

    public DianBaoLabel2(PrintPP_CPCL iPrinter, Context mContext) {
        super(iPrinter, mContext);
    }

    public void Lable(PrintModel printModel, String recordThing, String zhusong, String chaosong, String description) {

        this.recordThing = recordThing;
        this.zhusong = zhusong;
        this.chaosong = chaosong;
        this.description = description;
        this.year = printModel.year;
        this.month = printModel.month;
        this.day = printModel.day;
        print();
    }

    @Override
    protected void pageSetup() {
        int pageWidth = 566;
        int pageHeight = 760;

        setPageWidthAndHeight(pageWidth, pageHeight);
    }

    @Override
    protected void printMiddle() {

        int fontHeight = 35;
        int titleHeight = 48;

        int topFontPoint = fontPoint2;
        int topFont = 2;

        iPrinter.drawText(0, printTopPading + titleHeight, pageWidth, topFontPoint, recordThing, topFont, 0, 0, false, false);

        int zhusongL = zhusong.length();
        int zhusongPadding = 0;
        float num = (zhusongL * topFontPoint) / (float) pageWidth;
        if (num > 1) {
            zhusongPadding = (int) num * topFontPoint + 8;
        }

        iPrinter.drawText(0, printTopPading + titleHeight + fontHeight, pageWidth, topFontPoint, zhusong, topFont, 0, 0, false, false);
        iPrinter.drawText(0, printTopPading + titleHeight + fontHeight * 2 + zhusongPadding, pageWidth, pageHeight, chaosong, topFont, 0, 0, false, false);

        int chaosongL = chaosong.length();
        int chaosongPadding = 0;
        float chaosongNum = (chaosongL * topFontPoint) / (float) pageWidth;
        if (chaosongNum > 1) {
            chaosongPadding = ((int) chaosongNum) * topFontPoint + 8;
        }

        iPrinter.drawText(0, printTopPading + titleHeight + fontHeight * 3 + zhusongPadding + chaosongPadding, pageWidth, pageHeight, description, topFont, 0, 0, false, false);
    }

}
