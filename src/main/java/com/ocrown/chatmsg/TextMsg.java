package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;

public class TextMsg extends ChatMsg {

    String content;
    TextMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, String msgtype,int msgtime,String content) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.content=content;
    }
    TextMsg(String content){
        super();
        this.content=content;
    }
    public String getContent() {
        return content;
    }

    @Override
    Map<String, String> toMap() {
        Map<String,String> ret=super.toMap();
        ret.put("content", content);
        return ret;
    }

    public static ChatMsg msgFactory(String msgid,String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("content");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new TextMsg(StringOperator.value2String(map.get("content")));
    }
}
