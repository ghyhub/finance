package com.ocrown.chatmsg;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import com.ocrown.DataBase;
import com.ocrown.FileStorer;
import com.ocrown.StringOperator;
import com.ocrown.TimeOperator;

public class ChatrecordItem extends MsgItem {
    String type;
    long msgtime;
    ChatMsg content;
    Boolean fromchatroom;
    FileStorer fs;

    ChatrecordItem(String msgid, int index,String object, FileStorer fs) throws ClassNotFoundException, NoSuchMethodException,
            SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        super(msgid,index);
        Map<String,String>map=StringOperator.objectFromString(object);
        this.type=StringOperator.value2String(map.get("type"));
        this.msgtime=Long.parseLong(map.get("msgtime"));
        this.content=ChatMsg.msgFactory(msgid+"_"+index,0, type,StringOperator.value2String(map.get("content")), fs);
        if(map.get("fromchatroom").equals("true")){
            this.fromchatroom=true;
        }else{
            this.fromchatroom=false;
        }
    }

    Map<String,String>toMap(){
        Map<String,String>ret=super.toMap();
        ret.put("type", type);
        ret.put("msgtime", TimeOperator.timetamp2time(msgtime));
        if(fromchatroom){
            ret.put("fromchatroom", "true");
        }else{
            ret.put("fromchatroom", "false");
        }
        return ret;
    }

    @Override
    public void saveMsg(DataBase db, String table) throws SQLException {
        super.saveMsg(db, table);
        content.saveMsg(db,table);
    }

}
