package com.qr.demo.Label;

import com.qr.demo.model.PrintModel;
import com.qr.print.PrintPP_CPCL;

public class YjgzlkLabel {

    /**
     * @param iPrinter
     */
    public static void Lable(PrintPP_CPCL iPrinter, PrintModel printModel) {

        if (iPrinter == null || printModel == null) {
            return;
        }

        iPrinter.pageSetup(586, 800);

        //第一联

        iPrinter.drawText(0, 0, 586, 800, "客运记录", 2, 0, 0, false, false);

        iPrinter.drawText(0, 0, 586, 800, "记录事由" + printModel.thing, 2, 0, 0, false, false);

        iPrinter.drawText(0, 0, 586, 800, "" + "站", 2, 0, 0, false, false);


        String print = printModel.year + "年" + printModel.month + "月" + printModel.day + "日，" + printModel.trainNum + "次列车" + printModel.startStation + "站开车后，" +
                "旅客" + printModel.name + ",身份证号码" + printModel.cardNum + ",持" + printModel.beginStation + "站至" + printModel.stopStation + "站车票，" + "" +
                "票号" + printModel.ticketNum + ",找到列车长，自称坐过站。现交你站，请按章处理";


        iPrinter.print(0, 0);

    }
}
