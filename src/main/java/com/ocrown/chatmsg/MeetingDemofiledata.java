package com.ocrown.chatmsg;

import java.sql.SQLException;
import java.util.Map;

import com.ocrown.StringOperator;
import com.ocrown.TimeOperator;

public class MeetingDemofiledata extends MsgItem{
    String filename;
    String demooperator;
    long starttime;
    long endtime;
    MeetingDemofiledata(String msgid,int index,String filename,String demooperator,long starttime,long endtime){
        super(msgid, index);
        this.filename=filename;
        this.demooperator=demooperator;
        this.starttime=starttime;
        this.endtime=endtime;
    }
    MeetingDemofiledata(String msgid,int index,String object){
        super(msgid, index);
        Map<String,String>map=StringOperator.objectFromString(object);
        this.filename=StringOperator.value2String(map.get("filename"));
        this.demooperator=StringOperator.value2String(map.get("demooperator"));
        this.starttime=Long.parseLong(map.get("starttime"));
        this.endtime=Long.parseLong(map.get("endtime"));
    }

    @Override
    Map<String, String> toMap() {
        Map<String,String>ret= super.toMap();
        ret.put("filename",filename);
        ret.put("demooperator", demooperator);
        ret.put("starttime", TimeOperator.timetamp2time(starttime));
        ret.put("endtime", TimeOperator.timetamp2time(endtime));
        return ret;
    }

}
