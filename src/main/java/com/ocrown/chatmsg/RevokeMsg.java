package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;

public class RevokeMsg extends ChatMsg {

    String premsgid;

    RevokeMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, int msgtime,String msgtype,
            String premsgid) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.premsgid = premsgid;
    }
    RevokeMsg(String premsgid){
        super();
        this.premsgid=premsgid;
    }

    public String getPremsgid() {
        return premsgid;
    }

    @Override
    Map<String, String> toMap() {
        Map<String,String> ret=super.toMap();
        ret.put("pre_msgid", premsgid);
        return ret;
    }
    
    public static ChatMsg msgFactory(String msgid,String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("pre_msgid");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new RevokeMsg(StringOperator.value2String(map.get("pre_msgid")));
    }
}
