package com.qr.demo.bmob;

import java.io.Serializable;

import cn.bmob.v3.BmobObject;

/**
 * Created by sun on 2017/12/29.
 */

public class SaveModel extends BmobObject implements Serializable {
    public String uuid = "";//  uuid
    public String recordThing = "";//  记录事由
    public String trainNum = "";//车次
    public String recordtime = "";//记录时间
    public String content = "";

}
