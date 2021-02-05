package com.ocrown.chatmsg;

import java.util.Map;

import com.ocrown.TimeOperator;

public class SwitchMsg extends WeworkMsg{
    long time;
    String user;
    
    SwitchMsg(String msgid,String action,long time,String user){
        super(msgid, action);
        this.time=time;
        this.user=user;
    }
    
    @Override
    Map<String, String> toMap() {
        Map<String,String> ret = super.toMap();
        ret.put("time", TimeOperator.timetamp2time(time));
        ret.put("user", user);
        return ret;
    }
    
}
