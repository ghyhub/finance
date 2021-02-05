package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;

public class DocMsg extends ChatMsg {

    String title;
    String linkurl;
    String doccreator;

    DocMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, long msgtime,
            String msgtype,String title,String linkurl,String doccreator) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.title=title;
        this.linkurl=linkurl;
        this.doccreator=doccreator;
    }
    DocMsg(String title,String linkurl,String doccreator){
        this.title=title;
        this.linkurl=linkurl;
        this.doccreator=doccreator;
    }
    
    public String getTitle() {
        return title;
    }
    public String getLinkurl() {
        return linkurl;
    }
    public String getDoccreator() {
        return doccreator;
    }

    @Override
    Map<String, String> toMap() {
        Map<String,String>ret=super.toMap();
        ret.put("title", title);
        ret.put("link_url", linkurl);
        ret.put("creator", doccreator);
        return ret;
    }

    public static ChatMsg msgFactory(String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("title");keys.add("link_url");keys.add("doc_creator");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new DocMsg(StringOperator.value2String(map.get("title")),StringOperator.value2String(map.get("link_url")),
        StringOperator.value2String(map.get("doc_creator")));
    }

}
