package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;

public class CardMsg extends ChatMsg {

    String corpname;
    String userid;
    CardMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, int msgtime,
            String msgtype,String corpname,String userid) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.corpname=corpname;
        this.userid=userid;
    }
    
    CardMsg(String corpname,String userid){
        super();
        this.corpname=corpname;
        this.userid=userid;
    }

    public String getCorpname() {
        return corpname;
    }
    public String getUserid() {
        return userid;
    }

    @Override
    Map<String, String> toMap() {
        Map<String,String> ret=super.toMap();
        ret.put("corpname", corpname);
        ret.put("userid", userid);
        return ret;
    }

    public static ChatMsg msgFactory(String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("corname");
        keys.add("userid");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new CardMsg(StringOperator.value2String(map.get("corname")),StringOperator.value2String(map.get("userid")));
    }
}
