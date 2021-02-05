package com.ocrown.chatmsg;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import com.ocrown.DataBase;
import com.ocrown.FileStorer;
import com.ocrown.StringOperator;
import com.ocrown.TimeOperator;

public abstract class ChatMsg {
    String msgid;
    String action;
    String from;
    Vector<String> tolist;
    String roomid;
    long msgtime;
    String msgtype;

    ChatMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, long msgtime,
            String msgtype) {
        this.msgid = msgid;
        this.action = action;
        this.from = from;
        this.tolist = tolist;
        this.roomid = roomid;
        this.msgtime = msgtime;
        this.msgtype = msgtype;
    }

    ChatMsg() {
    }

    public String getMsgid() {
        return msgid;
    }

    public String getAction() {
        return action;
    }

    public String getFrom() {
        return from;
    }

    public Vector<String> getTolist() {
        return tolist;
    }

    public String getRoomid() {
        return roomid;
    }

    public String getMsgtype() {
        return msgtype;
    }

    Map<String, String> toMap() {
        Map<String, String> ret = new HashMap<String, String>();
        ret.put("msgid", msgid);
        ret.put("action", action);
        ret.put("from", from);
        String stolist = "[";
        for (Iterator<String> i = tolist.iterator(); i.hasNext();) {
            stolist = stolist + i.next();
            if (i.hasNext()) {
                stolist = stolist + ",";
            } else {
                stolist = stolist + "]";
            }
        }
        ret.put("tolist", stolist);
        ret.put("roomid", roomid);
        ret.put("msgtime",TimeOperator.timetamp2time(msgtime));
        ret.put("msgtype", msgtype);
        return ret;
    }

    public void saveMsg(DataBase db, String table) throws SQLException {
        String statement=db.generateInsertStatement(table,toMap(),"msgid");
        db.sendData(statement);
    }

    public static ChatMsg msgFactory(String chatdatal3,FileStorer fs) throws ClassNotFoundException, NoSuchMethodException,
            SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        //key=[msgid,action,from,tolist,roomid,msgtime,msgtype,?]
        Map<String,String>map=StringOperator.objectFromString(chatdatal3);
        String clasname="com.ocrown.chatmsg."+StringOperator.underline2Bighump(StringOperator.value2String(map.get("msgtype")))+"Msg";
        Class<?> subclas=Class.forName(clasname);
        Method submethod=subclas.getMethod("msgFactory", chatdatal3.getClass(),fs.getClass());
        String subdata=map.get(StringOperator.value2String(map.get("msgtype")));
        ChatMsg result=(ChatMsg)submethod.invoke(null, new Object[]{subdata,fs});
        result.msgid=StringOperator.value2String(map.get("msgid"));
        result.action=StringOperator.value2String(map.get("action"));
        result.from=StringOperator.value2String(map.get("from"));
        result.tolist=StringOperator.list2StringList(StringOperator.listFromString(map.get("tolist")));
        result.roomid=StringOperator.value2String(map.get("roomid"));
        result.msgtime=Long.parseLong(map.get("msgtime"));
        result.msgtype=StringOperator.value2String(map.get("msgtype"));
        return result;
    }
}
