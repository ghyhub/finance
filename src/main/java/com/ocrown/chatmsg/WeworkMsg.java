package com.ocrown.chatmsg;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ocrown.DataBase;

public abstract class WeworkMsg {
    String msgid;
    String action;

    WeworkMsg(String msgid, String action) {
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
        String statement=db.generateInsertStatement(table,toMap());
        db.sendData(statement);
    }
}
