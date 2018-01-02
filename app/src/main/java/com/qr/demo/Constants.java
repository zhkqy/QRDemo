package com.qr.demo;

import java.util.ArrayList;

/**
 * Created by sun on 2017/12/28.
 */

public class Constants {

    /**
     * 客运记录模板
     *
     * @return
     */
    public static ArrayList<String> getPassengerRecordList() {

        ArrayList<String> list = new ArrayList<>();

        list.add("移交过站旅客");
        list.add("移交患病旅客");
        list.add("持挂失补车票中途下车到站退款");
        list.add("挂失补车票到站退款");
        list.add("移交未换纸质车票旅客");
        list.add("移交无票人员");
        list.add("移交列车晚点中转旅客");
        list.add("移交误乘旅客");
        list.add("丢失车票补票后又找到原票到站退票");
        list.add("移交精神异常旅客");
        list.add("移交遗失物品");
        list.add("移交危险品");

        list.add("误售、误购到站退票");
        list.add("车辆故障到站退款");
        list.add("移交烫伤旅客");
        list.add("移交不明物体击伤旅客");
        list.add("移交砸伤旅客");
        list.add("移交挤手旅客");

        return list;
    }

    /**
     * 列车电报模板
     *
     * @return
     */
    public static ArrayList<String> getTrainTelegramList() {

        ArrayList<String> list = new ArrayList<>();

        list.add("超员电报");
        list.add("旅客意外伤电报");
        list.add("石击列车电报");

        return list;
    }


}
