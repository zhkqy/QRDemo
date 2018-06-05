package com.qr.demo.Label;

import android.content.Context;
import android.text.TextUtils;

import com.qr.demo.model.PrintModel;
import com.qr.print.PrintPP_CPCL;

public class keYunRecordLabel extends KeYunBaseLabel {

    String recordThing;
    String connectStation;
    String description;
    String attachContent;


    public keYunRecordLabel(PrintPP_CPCL iPrinter, Context context) {
        super(iPrinter, context);
    }

    public void Lable(PrintModel printModel, String recordThing, String connectStation, String description, String attachContent) {

        this.recordThing = recordThing;
        this.connectStation = connectStation;
        this.description = description;
        this.attachContent = attachContent;

        this.year = printModel.year;
        this.month = printModel.month;
        this.day = printModel.day;

    }


    @Override
    protected void pageSetup() {
        int pageWidth = 566;
        int pageHeight = 800;

        setPageWidthAndHeight(pageWidth, pageHeight);
    }

    @Override
    protected void printMiddle() {

//        int fontHeight = 35;
//        int titleHeight = 48;
//
//        int topFontPoint = fontPoint2;
//        int topFont = 2;
//
//        iPrinter.drawText(0, printTopPading + titleHeight, pageWidth, topFontPoint, "记录事由：", topFont, 0, 1, false, false);
//        iPrinter.drawText(fontPoint2 * 5, printTopPading + titleHeight, pageWidth, topFontPoint, recordThing.replace("(无第三者责任)", ""), topFont, 0, 0, false, false);
//        iPrinter.drawText(0, printTopPading + titleHeight + fontHeight, pageWidth, topFontPoint, connectStation, topFont, 0, 0, false, false);
//        iPrinter.drawText(0, printTopPading + titleHeight + fontHeight * 2, pageWidth, pageHeight, description, topFont, 0, 0, false, false);
//
//
//        int descriptionL = description.length();
//        int descPadding = 0;
//        float num = (descriptionL * topFontPoint) / (float) pageWidth;
//        if (num > 1) {
//            descPadding = (int) (num * topFontPoint + 8);
//        }
//
//        if (!TextUtils.isEmpty(attachContent)) {
//            iPrinter.drawText(0, printTopPading + titleHeight + fontHeight * 3 + descPadding, pageWidth, pageHeight, attachContent, topFont, 0, 0, false, false);
//        }
    }


    public void Lable() {
        print();
    }
}
