package com.qr.demo.Label;

import com.qr.print.PrintPP_CPCL;

public class keYunRecordLabel extends BaseLabel {

    String recordThing;
    String connectStation;
    String description;


    public keYunRecordLabel(PrintPP_CPCL iPrinter) {
        super(iPrinter);
    }

    public void Lable(String recordThing, String connectStation, String description) {

        this.recordThing = recordThing;
        this.connectStation = connectStation;
        this.description = description;

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

    }


}
