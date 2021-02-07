package com.ocrown.converse;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import com.ocrown.DataBase;
import com.ocrown.FileStorer;
import com.ocrown.StringOperator;
import com.ocrown.TimeOperator;
import com.ocrown.chatmsg.ChatMsg;

public class SendConverse extends WeworkConverse {
    String from;
    Vector<String> tolist;
    String roomid;
    long msgtime;
    String msgtype;
    ChatMsg typemsg;

    SendConverse(String msgid, String action) {
        super(msgid, action);
    }

    SendConverse(String from, Vector<String> tolist, String roomid, long msgtime, String msgtype, ChatMsg typemsg) {
        super();
        this.from = from;
        this.tolist = tolist;
        this.roomid = roomid;
        this.msgtime = msgtime;
        this.msgtype = msgtype;
        this.typemsg = typemsg;
    }

    @Override
    Map<String, String> toMap() {
        Map<String, String> ret = super.toMap();
        ret.put("from", from);
        ret.put("tolist", StringOperator.list2String(tolist));
        ret.put("roomid", roomid);
        ret.put("msgtime", TimeOperator.timetamp2time(msgtime));
        ret.put("msgtype", msgtype);
        return ret;
    }

    @Override
    public void saveMsg(DataBase db, String table) throws SQLException {
        super.saveMsg(db, table);
        typemsg.saveMsg(db, StringOperator.underline2Lowhump(msgtype)+ "msgtable");
    }

    public static WeworkConverse conFactory(String data, FileStorer fs)
            throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException,
            IllegalArgumentException, InvocationTargetException {
        Map<String, String> map = StringOperator.objectFromString(data);
        String msgid = StringOperator.value2String(map.get("msgid"));
        String msgtype = StringOperator.value2String(map.get("msgtype"));
        String subdata = map.get(StringOperator.value2String(map.get("msgtype")));
        ChatMsg typemsg = ChatMsg.msgFactory(msgid,0, msgtype, subdata, fs);
        return new SendConverse(StringOperator.value2String(map.get("from")),
                StringOperator.list2StringList(StringOperator.listFromString(map.get("tolist"))),
                StringOperator.value2String(map.get("roomid")), Long.parseLong(map.get("msgtime")),
                StringOperator.value2String(map.get("msgtype")), typemsg);
    }
}
