package com.ocrown.chatmsg;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;

import com.ocrown.DataBase;
import com.ocrown.FileStorer;
import com.ocrown.StringOperator;

public class MixedItem extends MsgItem {
    String type;
    ChatMsg content;
    FileStorer fs;

    MixedItem(String msgid, int index, String object, FileStorer fs)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        super(msgid, index);
        this.fs=fs;
        Map<String,String>map=StringOperator.objectFromString(object);
        this.type=StringOperator.value2String(map.get("type"));
        content=ChatMsg.msgFactory(msgid+"_"+index, 0, type, map.get("contnet"),fs);
    }

    @Override
    Map<String, String> toMap() {
        Map<String,String>ret=super.toMap();
        ret.put("type", type);
        return ret;
    }       

    @Override
    public void saveMsg(DataBase db, String table) throws SQLException {
        super.saveMsg(db, table);
        content.saveMsg(db, StringOperator.underline2Lowhump(type)+"msgtable");
    }
}
