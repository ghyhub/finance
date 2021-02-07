package com.ocrown.chatmsg;

import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import com.ocrown.DataBase;
import com.ocrown.StringOperator;

public class CollectMsg extends ChatMsg {

    String roomname;
    String creator;
    String createtime;
    String title;
    Vector<CollectItem> details;

    CollectMsg(String roomname,String creator,String createtime,String title,Vector<CollectItem>detatails){
        this.roomname=roomname;
        this.creator=creator;
        this.createtime=createtime;
        this.title=title;
        this.details=detatails;
    }

    public String getRoomname() {
        return roomname;
    }

    public String getCreator() {
        return creator;
    }

    public String getCreatetime() {
        return createtime;
    }
    
    public String getTitle() {
        return title;
    }
    
    public Vector<CollectItem> getDetails() {
        return details;
    }
    
    @Override
    Map<String, String> toMap() {
        Map<String,String> ret=super.toMap();
        ret.put("room_name", roomname);
        ret.put("creator", creator);
        ret.put("create_time", createtime);
        ret.put("title", title);
        return ret;
    }

    @Override
    public void saveMsg(DataBase db, String table) throws SQLException {
        super.saveMsg(db, table);
        for(CollectItem cli:details){
            cli.saveMsg(db, "collectdetailtable");
        }
    }

    public static ChatMsg msgFactory(String msgid,String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("msgid");keys.add("room_name");keys.add("creator");
        keys.add("create_time");keys.add("title");keys.add("detail");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        Vector<String>detatilstr=StringOperator.listFromString(map.get("detail"));
        Vector<CollectItem>detatilobj=new Vector<>();
        for(int i=0;i<detatilstr.size();i++){
            detatilobj.add(new CollectItem(msgid, i, detatilstr.get(i)));
        }
        return new CollectMsg(StringOperator.value2String(map.get("room_name")),StringOperator.value2String(map.get("creator")),
        StringOperator.value2String(map.get("create_time")),StringOperator.value2String(map.get("title")),detatilobj);
    }

}
