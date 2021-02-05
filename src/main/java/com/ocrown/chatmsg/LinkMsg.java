package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;

public class LinkMsg extends ChatMsg {

    String title;
    String description;
    String linkurl;
    String imageurl;

    LinkMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, int msgtime,
            String msgtype,String title,String description,String linkurl,String imageurl) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.title=title;
        this.description=description;
        this.linkurl=linkurl;
        this.imageurl=imageurl;
    }
    LinkMsg(String title,String description,String linkurl,String imageurl){
        super();
        this.title=title;
        this.description=description;
        this.linkurl=linkurl;
        this.imageurl=imageurl;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
    public String getLinkurl() {
        return linkurl;
    }
    public String getImageurl() {
        return imageurl;
    }
    @Override
    Map<String, String> toMap() {
        Map<String,String> ret=super.toMap();
        ret.put("title", title);
        ret.put("description", description);
        ret.put("link_url", linkurl);
        ret.put("image_url",imageurl);
        return ret;
    }

    public static ChatMsg msgFactory(String msgid,String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("title");keys.add("description");keys.add("link_url");keys.add("image_url");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new LinkMsg(StringOperator.value2String(map.get("title")),StringOperator.value2String(map.get("description")),
        StringOperator.value2String(map.get("link_url")),StringOperator.value2String(map.get("image_url")));
    }
}


