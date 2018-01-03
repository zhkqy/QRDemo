package com.qr.demo.model;

import java.io.Serializable;

/**
 * Created by sun on 2017/12/29.
 */

public class PrintModel implements Serializable {


    public String uuid = "";//  uuid

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

    public String goods = "";//携带物品

    public String actualStation = "";   //实际到站

    public String beforStation = "";   //在哪一站之前

    public String limitNum = "";//定员
    public String haveNum = "";  //现有人数

    public String otherBeginStation = "";  //other发站
    public String otherStopStation = "";  //other发站到站
    public String otherTicketNum = "";  //other发站票号
    public String otherCarriageNum = "";  //other发站车厢号
    public String otherSeatNum = "";  //other发站座位号
    public String otherAge = "";  //other旅客年龄
    public String otherSex = "";  //other旅客性别
    public String otherCardNum = "";  //other旅客身份证号
    public String otherName = "";  //other旅客姓名

    public String runBeginStation = "";  //运行开始站
    public String runStopStation = "";  //运行结束站


    public String zhusongBeginStation = "";  //主送发站
    public String zhusongStopStation = "";  //主送到站
    public String chaoyuanStation = "";  //超员车站


    public String troubleStation = "";  //事故车站
    public String troubleRecord = "";//事故记录

    public String troubleMinute = "";  //事故时间

    public String jicheOne = "";  //机车顺位1
    public String jicheTwo = "";  //机车顺位2
    public String jicheThree = "";  //机车顺位3

    public String glassNum = "";  //玻璃破损块数

    public String repalce1 = "";  //repalce1
    public String repalce2 = "";  //repalce2
    public String repalce3 = "";  //repalce3

    //客户记录 3条
    public String saveRecordThing = "";//  记录事由
    public String saveConnectStation = "";//  记录交接站
    public String savedescription = "";//  描述

    //电报4条
    public String saveZhusongDianBao = "";//  记录事由
    public String saveChaosongDianBao = "";//  记录交接站

    public long saveCreateTime = 0;  //保存创建时间

}
