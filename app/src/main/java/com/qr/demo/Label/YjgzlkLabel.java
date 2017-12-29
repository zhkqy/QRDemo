package com.qr.demo.Label;

import com.qr.print.PrintPP_CPCL;

public class YjgzlkLabel {

    /**
     * @param iPrinter
     */
    public static void Lable(PrintPP_CPCL iPrinter, String recordThing, String connectStation, String description) {

        if (iPrinter == null) {
            return;
        }

        int pageWidth = 576;
        int pageHeight = 750;


        iPrinter.pageSetup(pageWidth, pageHeight);

        //第一联

        int top = 10;
        int bottom = 40;

        int fontHeight = 35;
        int titleHeight = 48;

        iPrinter.drawText(pageWidth / 2 - 32 * 2, top, pageWidth, titleHeight, "客运记录", 3, 0, 0, false, false);
        iPrinter.drawText(0, top + titleHeight, pageWidth, fontHeight, recordThing, 2, 0, 0, false, false);
        iPrinter.drawText(0, top + titleHeight + fontHeight, pageWidth, fontHeight, connectStation, 2, 0, 0, false, false);
        iPrinter.drawText(0, top + titleHeight + fontHeight * 2, pageWidth, pageHeight, description, 2, 0, 0, false, false);



        iPrinter.drawText(pageWidth - 12 * 24 - 24, pageHeight - bottom - 24 - 24 * 2 - 12 - 12 - 24 * 2 - 12 - 12, 24, 24 * 2,
                "站段", 2, 0, 0, false, false);
        iPrinter.drawText(pageWidth - 12 * 24, pageHeight - bottom - 24 - 24 * 2 - 12 - 12 - 24 * 2 - 12, pageWidth, fontHeight,
                "编制人员　　　　　（印）", 2, 0, 0, false, false);

        iPrinter.drawText(pageWidth - 12 * 24 - 24, pageHeight - bottom - 24 - 24 * 2 - 12 - 12, 24, 24 * 2,
                "站段", 2, 0, 0, false, false);
        iPrinter.drawText(pageWidth - 12 * 24, pageHeight - bottom - 24 - 24 * 2 - 12, pageWidth, fontHeight,
                "签收人员　　　　　（印）", 2, 0, 0, false, false);

        iPrinter.drawText(pageWidth - 17 * 24, pageHeight - bottom - 24, pageWidth, fontHeight,
                "20　　　年　　　月　　　日编制　　", 2, 0, 0, false, false);

        iPrinter.print(0, 0);

    }
}
