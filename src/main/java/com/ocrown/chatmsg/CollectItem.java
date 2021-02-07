package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;

public class CollectItem extends MsgItem{
    long id;
    String que;
    String type;
    CollectItem(String msgid,int index,String object){
        super(msgid,index);
        Vector<String>keys=new Vector<>();
        keys.add("id");keys.add("que");keys.add("type");
        Map<String,String>map=StringOperator.objectFromString(object, keys);
        this.id=Long.parseLong(map.get("type"));
        this.que=StringOperator.value2String(map.get("que"));
        this.type=StringOperator.value2String(map.get("type"));
    }

    Map<String,String>toMap(){
        Map<String,String>ret=super.toMap();
        ret.put("id", Long.toString(id));
        ret.put("que", que);
        ret.put("type", type);
        return ret;
    }
}
