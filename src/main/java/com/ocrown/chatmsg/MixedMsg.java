package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;

public class MixedMsg extends ChatMsg {

    Vector<MixedItem>items;
    MixedMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, long msgtime,
            String msgtype,Vector<MixedItem>items) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.items=items;
    }
    MixedMsg(){
        super();
    }

    public Vector<MixedItem> getItems() {
        return items;
    }

    @Override
    Map<String, String> toMap() {
        return super.toMap();
    }

    public static ChatMsg msgFactory(String chatdatal3,Object fs){
        return new MixedMsg();
    }
    
}
