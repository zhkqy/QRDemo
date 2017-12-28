package com.qr.demo.Label;

import com.qr.print.PrintPP_CPCL;

public class YjgzlkLabel {

    /**
     * @param iPrinter
     * @param thing        记录事由
     * @param startStation 开始站
     * @param year
     * @param month
     * @param day
     * @param trainNum     车次
     * @param name         旅客名称
     * @param cardNum      身份证号码
     * @param beginStation 旅客买的票 的开始位置
     * @param stopStation  旅客买的票 的结束位置
     * @param ticketNum    票号
     */
    public static void Lable(PrintPP_CPCL iPrinter, String thing, String startStation,
                             String year, String month, String day, String trainNum, String name, String cardNum,
                             String beginStation, String stopStation, String ticketNum) {

        iPrinter.pageSetup(586, 800);

        //第一联

        iPrinter.drawText(0, 0, 586, 800, "客运记录", 2, 0, 0, false, false);

        iPrinter.drawText(0, 0, 586, 800, "记录事由" + thing, 2, 0, 0, false, false);

        iPrinter.drawText(0, 0, 586, 800, "" + "站", 2, 0, 0, false, false);


        String print = year + "年" + month + "月" + day + "日，" + trainNum + "次列车" + startStation + "站开车后，" +
                "旅客" + name + ",身份证号码" + cardNum + ",持" + beginStation + "站至" + stopStation + "站车票，" + "" +
                "票号" + ticketNum + ",找到列车长，自称坐过站。现交你站，请按章处理";


        iPrinter.print(0, 0);

    }
}
