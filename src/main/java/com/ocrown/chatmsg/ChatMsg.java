package com.ocrown.chatmsg;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ocrown.DataBase;
import com.ocrown.FileStorer;
import com.ocrown.StringOperator;

public abstract class ChatMsg {

    String msgid;

    ChatMsg() {
    }

    Map<String, String> toMap() {
        Map<String, String> ret = new HashMap<String, String>();
        return ret;
    }

    public void saveMsg(DataBase db, String table) throws SQLException {
        String statement=db.generateInsertStatement(table,toMap());
        db.sendData(statement);
    }

    public static ChatMsg msgFactory(String msgid,String msgtype,String data,FileStorer fs)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        String clasname="com.ocrown.chatmsg."+StringOperator.underline2Bighump(msgtype)+"Msg";
        Class<?> subclas=Class.forName(clasname);
        Method submethod=subclas.getMethod("msgFactory", msgid.getClass(), data.getClass(),fs.getClass());
        ChatMsg result=(ChatMsg)submethod.invoke(null, new Object[]{msgid, data,fs});
        result.msgid=msgid;
        return result;
    }
}
