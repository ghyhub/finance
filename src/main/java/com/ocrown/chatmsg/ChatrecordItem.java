package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;
import com.ocrown.TimeOperator;

public class ChatrecordItem extends MsgItem{
    String type;
    long msgtime;
    String content;
    Boolean fromchatroom;
    ChatrecordItem(String msgid,String object){
        super(msgid);
        Vector<String>keys=new Vector<>();
        keys.add("type");keys.add("msgtime");keys.add("content");keys.add("fromchatroom");
        Map<String,String>map=StringOperator.objectFromString(object, keys);
        this.type=StringOperator.value2String(map.get("type"));
        this.msgtime=Long.parseLong(map.get("msgtime"));
        this.content=StringOperator.value2String(map.get("content"));
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
        ret.put("content",content);
        if(fromchatroom){
            ret.put("fromchatroom", "true");
        }else{
            ret.put("fromchatroom", "false");
        }
        return ret;
    }

}
