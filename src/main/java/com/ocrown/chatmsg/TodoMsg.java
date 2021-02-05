package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;

public class TodoMsg extends ChatMsg {

    String title;
    String content;
    TodoMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, long msgtime,
            String msgtype,String title,String content) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.title=title;
        this.content=content;
    }
    TodoMsg(String title,String content){
        super();
        this.title=title;
        this.content=content;
    }
    
    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
    
    @Override
    Map<String, String> toMap() {
        Map<String,String> ret=super.toMap();
        ret.put("title",title);
        ret.put("content",content);
        return ret;
    }

    public static ChatMsg msgFactory(String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("title");
        keys.add("content");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new TodoMsg(StringOperator.value2String(map.get("title")),StringOperator.value2String(map.get("content")));
    }
}

