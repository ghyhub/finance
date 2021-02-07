package com.ocrown.chatmsg;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import com.ocrown.DataBase;
import com.ocrown.FileStorer;
import com.ocrown.StringOperator;

public class MixedMsg extends ChatMsg {

    Vector<MixedItem> items;

    MixedMsg(Vector<MixedItem>items) {
        this.items=items;
    }

    public Vector<MixedItem> getItems() {
        return items;
    }

    @Override
    Map<String, String> toMap() {
        return super.toMap();
    }

    @Override
    public void saveMsg(DataBase db, String table) throws SQLException {
        super.saveMsg(db, table);
        for(MixedItem mi:items){
            mi.saveMsg(db, "mixeditemtable");
        }
    }

    public static ChatMsg msgFactory(String msgid, String chatdatal3, FileStorer fs)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Map<String,String>map=StringOperator.objectFromString(chatdatal3);
        Vector<String>itemsstr=StringOperator.listFromString(map.get("item"));
        Vector<MixedItem>itemsobj=new Vector<>();
        for(int i=0;i<itemsstr.size();i++){
            itemsobj.add(new MixedItem(msgid, i, itemsstr.get(i),fs));
        }
        return new MixedMsg(itemsobj);
    }
    
}
