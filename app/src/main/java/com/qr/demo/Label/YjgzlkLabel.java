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

        iPrinter.pageSetup(pageWidth, 800);

        //第一联

        int top = 60;
        int fontHeight = 32;
        int titleHeight = 48;

        iPrinter.drawText(pageWidth / 2 - 32 * 2, top, pageWidth, titleHeight, "客运记录", 3, 0, 0, false, false);
        iPrinter.drawText(0, top + titleHeight, pageWidth, top + titleHeight, recordThing, 2, 0, 0, false, false);
        iPrinter.drawText(0, top + titleHeight + fontHeight, pageWidth, top + titleHeight + fontHeight, connectStation, 2, 0, 0, false, false);
        iPrinter.drawText(0, top + titleHeight + fontHeight * 2, pageWidth, top + titleHeight + fontHeight * 2, description, 2, 0, 0, false, false);

        iPrinter.print(0, 0);

    }
}
