package com.qr.demo.Label;

import com.qr.print.PrintPP_CPCL;

/**
 * Created by chenlei on 2017/12/30.
 */

public abstract class BaseLabel {

    protected PrintPP_CPCL iPrinter;

    //第一联
    public int topPadding = 10;
    public int printTopPading = 0;
    public int bottomPadding = 60;
    protected int pageWidth = 566;
    protected int pageHeight = 760;


    protected static int fontPoint1 = 16;
    protected static int fontPoint2 = 24;
    protected static int fontPoint3 = 32;

    public static String year;
    public static String month;
    public static String day;

    protected abstract void printMiddle();

    protected abstract void pageSetup();

    protected BaseLabel(PrintPP_CPCL iPrinter) {
        this.iPrinter = iPrinter;
    }

    protected abstract void printTop(int position);


    private void printBottom(int position) {

//        int bottomfontPoint = fontPoint1;
//        int bottomfont = 1;
//
//        //倒叙的顺序
//
//        int bottomY1 = position - bottomPadding - bottomfontPoint;
//        int bottomY2 = bottomY1 - bottomfontPoint * 2 - bottomfontPoint / 2;
//        int bottomY3 = bottomY2 - bottomfontPoint / 2;
//        int bottomY4 = bottomY3 - bottomfontPoint * 2 - bottomfontPoint / 2;
//        int bottomY5 = bottomY4 - bottomfontPoint / 2;
//
//
//        iPrinter.drawText(0, bottomY5 - bottomfontPoint * 3 - 8, pageWidth, bottomfontPoint,
//                "注：1.站、车需要编织记录时适用。", bottomfont, 0, 0, false, false);
//
//        iPrinter.drawText(0, bottomY5 - bottomfontPoint * 2, pageWidth, bottomfontPoint,
//                "　　2.本记录不能作为乘车凭证。", bottomfont, 0, 0, false, false);
//
//
//        iPrinter.drawText(pageWidth - 18 * bottomfontPoint, bottomY4, pageWidth, bottomfont,
//                "大连客运段编制人员　　　　　　（印）", bottomfont, 0, 0, false, false);
//
//        iPrinter.drawText(pageWidth - 14 * bottomfontPoint, bottomY2, pageWidth, bottomfont,
//                "站签收人员　　　　　　（印）", bottomfont, 0, 0, false, false);
//
//        String time = year + "年" + month + "月" + day + "日编制　";
//
//        iPrinter.drawText(pageWidth - 10 * bottomfontPoint, bottomY1, pageWidth, bottomfont,
//                time, bottomfont, 0, 0, false, false);

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
        printTop(0);
        printMiddle();
        printBottom(pageHeight);
        iPrinter.print(0, 0);
    }

}
