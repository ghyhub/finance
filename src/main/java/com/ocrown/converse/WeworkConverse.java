package com.ocrown.converse;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.FileStore;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ocrown.DataBase;
import com.ocrown.FileStorer;
import com.ocrown.StringOperator;
import com.ocrown.chatmsg.ChatMsg;

public abstract class WeworkConverse {
    String msgid;
    String action;

    WeworkConverse() {

    }

    WeworkConverse(String msgid, String action) {
        this.msgid = msgid;
        this.action = action;
    }

    Map<String, String> toMap() {
        Map<String, String> ret = new HashMap<>();
        ret.put("msgid", msgid);
        ret.put("action", action);
        return ret;
    }

    public void saveMsg(DataBase db, String table) throws SQLException {
        String statement = db.generateInsertStatement(table, toMap());
        db.sendData(statement);
    }

    public static WeworkConverse conFactory(String data, FileStorer fs)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Map<String,String>map=StringOperator.objectFromString(data);
        String msgid=StringOperator.value2String(map.get("msgid"));
        String action=StringOperator.value2String(map.get("action"));
        String classname="com.ocrown.chatmsg."+StringOperator.underline2Bighump(action)+"Converse";
        Class<?> subclas=Class.forName(classname);
        Method submethod=subclas.getMethod("conFactory", data.getClass(),fs.getClass());
        WeworkConverse result=(WeworkConverse)submethod.invoke(null, new Object[]{data,fs});
        result.msgid=msgid;
        result.action=action;
        return result;
    }
}
