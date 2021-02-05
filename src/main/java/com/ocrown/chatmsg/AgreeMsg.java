package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;
import com.ocrown.TimeOperator;

public class AgreeMsg extends ChatMsg {

    String userid;
    long agreetime;
    AgreeMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, int msgtime,
            String msgtype,String userid,long agreetime) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.userid=userid;
        this.agreetime=agreetime;
    }
    AgreeMsg(String userid,long agreetime){
        super();
        this.userid=userid;
        this.agreetime=agreetime;
    }

    public String getUserid() {
        return userid;
    }
    public long getAgreetime() {
        return agreetime;
    }

    @Override
    Map<String, String> toMap() {
        Map<String,String>ret=super.toMap();
        ret.put("userid", userid);
        ret.put("agree_time", TimeOperator.timetamp2time(agreetime));
        return ret;
    }

    public static ChatMsg msgFactory(String msgid,String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("userid");keys.add("agree_time");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new AgreeMsg(StringOperator.value2String(map.get("userid")),Long.parseLong(map.get("agree_time")));
    }
    
}
