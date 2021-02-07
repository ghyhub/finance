package com.ocrown.chatmsg;

import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import com.ocrown.DataBase;
import com.ocrown.StringOperator;

public class NewsMsg extends ChatMsg {

    Vector<NewsItem> items;

    NewsMsg(Vector<NewsItem>items){
        super();
        this.items=items;
    }
    public Vector<NewsItem> getItems() {
        return items;
    }
    
    @Override
    public void saveMsg(DataBase db, String table) throws SQLException {
        super.saveMsg(db, table);
        for(NewsItem ni:items){
            ni.saveMsg(db, "newsitemtable");
        }
    }

    public static ChatMsg msgFactory(String msgid,String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("msgid");keys.add("item");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        Vector<String>itemsstr=StringOperator.listFromString(map.get("item"));
        Vector<NewsItem>itemsobj=new Vector<>();
        for(int i=0;i<itemsstr.size();i++){
            itemsobj.add(new NewsItem(msgid, i, itemsstr.get(i)));
        }
        return new NewsMsg(itemsobj);
    }
    
}
