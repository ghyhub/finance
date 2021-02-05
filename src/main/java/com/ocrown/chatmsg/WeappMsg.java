package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;

public class WeappMsg extends ChatMsg {

    String title;
    String description;
    String username;
    String displayname;
    WeappMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, int msgtime,
            String msgtype,String title,String description,String username,String displayname) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.title=title;
        this.description=description;
        this.username=username;
        this.displayname=displayname;
    }
    WeappMsg(String title,String description,String username,String displayname){
        super();
        this.title=title;
        this.description=description;
        this.username=username;
        this.displayname=displayname;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getUsername() {
        return username;
    }
    public String getDisplayname() {
        return displayname;
    }

    @Override
    Map<String, String> toMap() {
        Map<String,String> ret=super.toMap();
        ret.put("title",title);
        ret.put("description",description);
        ret.put("username",username);
        ret.put("displayname", displayname);
        return ret;
    }

    public static ChatMsg msgFactory(String msgid,String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("title");keys.add("description");keys.add("username");keys.add("displayname");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new WeappMsg(StringOperator.value2String(map.get("title")),StringOperator.value2String(map.get("description")),
        StringOperator.value2String(map.get("username")),StringOperator.value2String(map.get("displayname")));
    }
}
