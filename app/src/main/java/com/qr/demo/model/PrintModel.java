package com.qr.demo.model;

import java.io.Serializable;

/**
 * Created by sun on 2017/12/29.
 */

public class PrintModel implements Serializable {

    public String recordThing = "";//  记录事由
    public String connectStation = "";//交接站
    public String year = "";
    public String month = "";
    public String day = "";
    public String trainNum = "";//车次
    public String name = "";// 旅客名称
    public String cardNum = "";//  身份证号码
    public String beginStation = "";// 旅客买的票 的开始位置
    public String stopStation = "";// 旅客买的票 的结束位置
    public String ticketNum = "";// 票号


    public String carriageNum = "";// 车厢号
    public String seatNum = "";// 座位号

    public String netOrderNum = "";//网购订单号
    public String netBeginStation = "";//网购原票发站
    public String netStopStation = "";//网购原票到站
    public String netErrorReason = "";//网购出错理由


    public String zishuStartStation = "";  //自述上站

    public String lateMinute = "";  //晚点分钟
    public String zhongzhuanTrainNum = "";  //中转车次
    public String zhongzhuanBeginStation = "";  //中转发站
    public String zhongzhuanStopStation = "";  //中转到站
    public String zhongzhuanTicketNum = "";  //中转票号
    public String zhongzhuanCarriageNum = "";  //中转车厢号
    public String zhongzhuanSeatNum = "";  //中转座位号

    public String wuchengTrainNum = "";  //误乘车次
    public String wuchengBeginStation = "";  //误乘发站
    public String wuchengStopStation = "";  //误乘到站
    public String wuchengTicketNum = "";  //误乘票号


    public String bupiaoBeginStation = "";  //补票发站
    public String bupiaoStopStation = "";  //补票到站
    public String bupiaoTicketNum = "";  //补票票号

    public String diupiaoStation = "";  //丢票车站
    public String foundStation = "";  //找到车票车站

    public String address = "";//家庭住址

    public String money = "";//现金

}
