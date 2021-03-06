package com.ocrown.chatmsg;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.ocrown.DataBase;

public class MsgItem {
    String msgid;
    int index;
    MsgItem(String msgid,int index){
        this.msgid=msgid;
        this.index=index;
    }

    Map<String,String>toMap(){
        Map<String,String>ret=new HashMap<>();
        ret.put("msgid", msgid);
        return ret;
    }

    public void saveMsg(DataBase db,String table) throws SQLException{
        String statement=db.generateInsertStatement(table, toMap(),"");
        db.sendData(statement);
    }
}
