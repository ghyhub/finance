package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;
public class MarkdownMsg extends ChatMsg {

    String content;

    MarkdownMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, long msgtime,
            String msgtype,String content) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.content=content;
    }
    MarkdownMsg(String content){
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

    public static ChatMsg msgFactory(String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("content");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new MarkdownMsg(StringOperator.value2String(map.get("content")));
    }
}
