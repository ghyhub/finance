package com.ocrown.chatmsg;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import com.ocrown.DataBase;
import com.ocrown.FileStorer;
import com.ocrown.StringOperator;

public class ChatrecordMsg extends ChatMsg {

    String title;
    Vector<ChatrecordItem> items;
    FileStorer fs;

    ChatrecordMsg(String title, Vector<ChatrecordItem> items, FileStorer fs) {
        super();
        this.title = title;
        this.items = items;
        this.fs = fs;
    }

    public String getTitle() {
        return title;
    }

    public Vector<ChatrecordItem> getItems() {
        return items;
    }

    @Override
    Map<String, String> toMap() {
        Map<String, String> ret = super.toMap();
        ret.put("title", title);
        return ret;
    }

    @Override
    public void saveMsg(DataBase db, String table) throws SQLException {
        super.saveMsg(db, table);
        for (ChatrecordItem cri : items) {
            cri.saveMsg(db, "chatrecorditemtable");
        }
    }

    public static ChatMsg msgFactory(String msgid, String chatdatal3, FileStorer fs)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Map<String,String>map=StringOperator.objectFromString(chatdatal3);
        Vector<String>itemsstr=StringOperator.listFromString(map.get("item"));
        Vector<ChatrecordItem>itemsobj=new Vector<>();
        for(int i=0;i<itemsstr.size();i++){
            itemsobj.add(new ChatrecordItem(msgid,i,itemsstr.get(i),fs));
        }
        return new ChatrecordMsg(StringOperator.value2String(map.get("title")),itemsobj,fs);
    }
}

