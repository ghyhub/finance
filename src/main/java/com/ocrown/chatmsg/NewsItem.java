package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;

public class NewsItem extends MsgItem {
    String title;
    String description;
    String url;
    String picurl;

    NewsItem(String msgid, int index,String title, String description, String url, String picurl) {
        super(msgid,index);
        this.title = title;
        this.description = description;
        this.url = url;
        this.picurl = picurl;
    }

    NewsItem(String msgid,int index, String object) {
        super(msgid,index);
        Vector<String> keys = new Vector<>();
        keys.add("title");
        keys.add("description");
        keys.add("url");
        keys.add("picurl");
        Map<String, String> map = StringOperator.objectFromString(object, keys);
        this.title = StringOperator.value2String(map.get("title"));
        this.description = StringOperator.value2String(map.get("description"));
        this.url = StringOperator.value2String(map.get("url"));
        this.picurl = StringOperator.value2String(map.get("picurl"));
    }

    @Override
    Map<String, String> toMap() {
        Map<String, String> ret = super.toMap();
        ret.put("title", title);
        ret.put("description", description);
        ret.put("url", url);
        ret.put("picurl", picurl);
        return ret;
    }
}
