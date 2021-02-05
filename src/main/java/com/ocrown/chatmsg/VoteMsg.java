package com.ocrown.chatmsg;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;


public class VoteMsg extends ChatMsg {

    String title;
    Vector<String>item;
    //投票类型.101发起投票、102参与投票
    long type;
    String id;

    VoteMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, long msgtime,
            String msgtype,String title,Vector<String>item,long type,String id) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.title=title;
        this.item=item;
        this.type=type;
        this.id=id;
    }
    VoteMsg(String title,Vector<String>item,long type,String id){
        super();
        this.title=title;
        this.item=item;
        this.type=type;
        this.id=id;
    }
    
    public String getTitle() {
        return title;
    }
    public Vector<String> getItem() {
        return item;
    }
    public long getType() {
        return type;
    }
    public String getId() {
        return id;
    }
    
    @Override
    Map<String, String> toMap() {
        Map<String,String> ret=super.toMap();
        ret.put("title", title);

        String sitem = "[";
        for (Iterator<String> i = item.iterator(); i.hasNext();) {
            sitem= sitem+ i.next();
            if (i.hasNext()) {
                sitem= sitem+ ",";
            } else {
                sitem= sitem+ "]";
            }
        }
        ret.put("item", sitem);

        ret.put("type", Long.toString(type));
        ret.put("id", id);
        return ret;
    }

    
    public static ChatMsg msgFactory(String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("title");keys.add("item");keys.add("type");keys.add("id");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new VoteMsg(StringOperator.value2String(map.get("title")),
        StringOperator.list2StringList(StringOperator.listFromString(map.get("tolist"))),
        Long.parseLong(map.get("type")),StringOperator.value2String(map.get("id")));
    }
}
