package com.qr.demo.Label;

import android.text.TextUtils;

import com.qr.print.PrintPP_CPCL;

public class keYunRecordLabel extends BaseLabel {

    String recordThing;
    String connectStation;
    String description;
    String attachContent;


    public keYunRecordLabel(PrintPP_CPCL iPrinter) {
        super(iPrinter);
    }

    public void Lable(String recordThing, String connectStation, String description, String attachContent) {

        this.recordThing = recordThing;
        this.connectStation = connectStation;
        this.description = description;
        this.attachContent = attachContent;

        print();
    }


    @Override
    protected void pageSetup() {
        int pageWidth = 576;
        int pageHeight = 760;

        setPageWidthAndHeight(pageWidth, pageHeight);
    }

    @Override
    protected void printUp() {

        int fontHeight = 35;
        int titleHeight = 48;

        int topFontPoint = fontPoint2;
        int topFont = 2;

        iPrinter.drawText(pageWidth / 2 - fontPoint3 * 2, topPadding, pageWidth, fontPoint3, "客运记录", 3, 0, 0, false, false);
        iPrinter.drawText(0, topPadding + titleHeight, pageWidth, topFontPoint, recordThing, topFont, 0, 0, false, false);
        iPrinter.drawText(0, topPadding + titleHeight + fontHeight, pageWidth, topFontPoint, connectStation, topFont, 0, 0, false, false);
        iPrinter.drawText(0, topPadding + titleHeight + fontHeight * 2, pageWidth, pageHeight, description, topFont, 0, 0, false, false);


        int descriptionL = description.length();
        int descPadding = 0;
        float num = (descriptionL * topFontPoint) / (float) pageWidth;
        if (num > 1) {
            descPadding = ((int) num) * topFontPoint + 8;
        }
        if (!TextUtils.isEmpty(attachContent)) {
            iPrinter.drawText(0, topPadding + titleHeight + fontHeight * 3 + descPadding, pageWidth, pageHeight, attachContent, topFont, 0, 0, false, false);
        }
    }


}
