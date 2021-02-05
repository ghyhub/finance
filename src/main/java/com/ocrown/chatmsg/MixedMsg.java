package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;
public class MixedMsg extends ChatMsg {

    Vector<MixedItem>items;

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

    public static ChatMsg msgFactory(String msgid,String chatdatal3,Object fs){
        return new MixedMsg();
    }
    
}
