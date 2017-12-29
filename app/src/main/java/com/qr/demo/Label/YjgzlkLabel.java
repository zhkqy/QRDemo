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

        iPrinter.pageSetup(586, 800);

        //第一联

        iPrinter.drawText(0, 0, 586, 34, "客运记录", 2, 0, 0, false, false);
        iPrinter.drawText(0, 34, 586, 34, recordThing, 2, 0, 0, false, false);
        iPrinter.drawText(0, 34 * 2, 586, 34 * 2, connectStation, 2, 0, 0, false, false);
        iPrinter.drawText(0, 34 * 3, 586, 34 * 3, description, 2, 0, 0, false, false);

        iPrinter.print(0, 0);

    }
}
