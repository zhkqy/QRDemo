package com.qr.demo.Label;

import com.qr.print.PrintPP_CPCL;

/**
 * Created by chenlei on 2017/12/30.
 */

public abstract class BaseLabel {

    protected PrintPP_CPCL iPrinter;

    //第一联
    int topPadding = 10;
    int bottomPadding = 60;
    protected int pageWidth = 576;
    protected int pageHeight = 760;


    protected static int fontPoint1 = 16;
    protected static int fontPoint2 = 24;
    protected static int fontPoint3 = 32;

    protected abstract void printUp();

    protected abstract void pageSetup();

    protected BaseLabel(PrintPP_CPCL iPrinter) {
        this.iPrinter = iPrinter;
    }

    protected void printDown() {

        int bottomfontPoint = fontPoint1;
        int bottomfont = 1;

        //倒叙的顺序

        int bottomY1 = pageHeight - bottomPadding - bottomfontPoint;
        int bottomY2 = bottomY1 - bottomfontPoint * 2 - bottomfontPoint / 2;
        int bottomY3 = bottomY2 - bottomfontPoint / 2;
        int bottomY4 = bottomY3 - bottomfontPoint * 2 - bottomfontPoint / 2;
        int bottomY5 = bottomY4 - bottomfontPoint / 2;


        iPrinter.drawText(0, bottomY5 - bottomfontPoint * 3, pageWidth, bottomfontPoint,
                "注：1.站、车需要编织记录时适用。", bottomfont, 0, 0, false, false);

        iPrinter.drawText(0, bottomY5 - bottomfontPoint * 2, pageWidth, bottomfontPoint,
                "　　2.本记录不能作为乘车凭证。", bottomfont, 0, 0, false, false);


        iPrinter.drawText(pageWidth - 12 * bottomfontPoint - bottomfontPoint, bottomY5, bottomfontPoint, bottomfontPoint * 2,
                "站段", bottomfont, 0, 0, false, false);

        iPrinter.drawText(pageWidth - 12 * bottomfontPoint, bottomY4, pageWidth, bottomfont,
                "编制人员　　　　　（印）", bottomfont, 0, 0, false, false);


        iPrinter.drawText(pageWidth - 12 * bottomfontPoint - bottomfontPoint, bottomY3, bottomfontPoint, bottomfontPoint * 2,
                "站段", bottomfont, 0, 0, false, false);

        iPrinter.drawText(pageWidth - 12 * bottomfontPoint, bottomY2, pageWidth, bottomfont,
                "签收人员　　　　　（印）", bottomfont, 0, 0, false, false);


        iPrinter.drawText(pageWidth - 17 * bottomfontPoint, bottomY1, pageWidth, bottomfont,
                "20　　　年　　　月　　　日编制　　", bottomfont, 0, 0, false, false);

        iPrinter.print(0, 0);

    }


    /**
     * 子类必须调用的方法
     *
     * @param pageWidth
     * @param pageHeight
     */
    protected void setPageWidthAndHeight(int pageWidth, int pageHeight) {
        this.pageWidth = pageWidth;
        this.pageHeight = pageHeight;
        iPrinter.pageSetup(pageWidth, pageHeight);
    }

    /**
     * 子类必须调用的方法
     */
    protected void print() {
        if (iPrinter == null) {
            return;
        }
        pageSetup();
        printUp();
        printDown();
    }

}
