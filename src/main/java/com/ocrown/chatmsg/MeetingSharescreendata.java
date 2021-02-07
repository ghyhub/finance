package com.ocrown.chatmsg;

import java.util.Map;

import com.ocrown.StringOperator;
import com.ocrown.TimeOperator;

public class MeetingSharescreendata extends MsgItem{
    String share;
    long starttime;
    long endtime;   
    MeetingSharescreendata(String msgid,int index,String object){
        super(msgid, index);
        Map<String,String>map=StringOperator.objectFromString(object);
        this.share=StringOperator.value2String(map.get("share"));
        this.starttime=Long.parseLong(map.get("starttime"));
        this.endtime=Long.parseLong(map.get("endtime"));
    }

    @Override
    Map<String, String> toMap() {
        Map<String,String>ret=super.toMap();
        ret.put("share", share);
        ret.put("starttime",TimeOperator.timetamp2time(starttime));
        ret.put("endtime", TimeOperator.timetamp2time(endtime));
        return ret;
    }
}
