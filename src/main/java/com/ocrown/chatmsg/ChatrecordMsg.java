package com.ocrown.chatmsg;

import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import com.ocrown.DataBase;
import com.ocrown.StringOperator;

public class ChatrecordMsg extends ChatMsg {

    String title;
    Vector<ChatrecordItem>items;
    ChatrecordMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, int msgtime,
            String msgtype,String title,Vector<ChatrecordItem>items){
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.title=title;
        this.items=items;
    }
    ChatrecordMsg(String title,Vector<ChatrecordItem>items){
        super();
        this.title=title;
        this.items=items;
    }
    public String getTitle() {
        return title;
    }
    public Vector<ChatrecordItem> getItems() {
        return items;
    }
    @Override
    Map<String, String> toMap() {
        Map<String,String> ret=super.toMap();
        ret.put("title", title);
        return ret;
    }

    @Override
    public void saveMsg(DataBase db, String table) throws SQLException {
        super.saveMsg(db, table);
        for(ChatrecordItem cri:items){
            cri.saveMsg(db, "chatrecord");
        }
    }

    public static ChatMsg msgFactory(String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("msgid");keys.add("title");keys.add("item");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        Vector<String>itemsstr=StringOperator.listFromString(map.get("item"));
        Vector<ChatrecordItem>itemsobj=new Vector<>();
        for(String s:itemsstr){
            itemsobj.add(new ChatrecordItem(StringOperator.value2String(map.get("msgid")),s));
        }
        return new ChatrecordMsg(StringOperator.value2String(map.get("title")),itemsobj);
    }
}

