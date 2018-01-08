package com.qr.demo.bmob;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by sun on 2017/12/29.
 */

public class SaveModel extends BmobObject implements Serializable {
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
}
