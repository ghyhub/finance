package com.ocrown.converse;

import java.nio.file.FileStore;
import java.util.Map;

import com.ocrown.FileStorer;
import com.ocrown.StringOperator;
import com.ocrown.TimeOperator;

public class SwitchConverse extends WeworkConverse{
    long time;
    String user;
    
    SwitchConverse(long time,String user){
        super();
        this.time=time;
        this.user=user;
    }
    SwitchConverse(String msgid,String action,long time,String user){
        super(msgid, action);
        this.time=time;
        this.user=user;
    }
    
    @Override
    Map<String, String> toMap() {
        Map<String,String> ret = super.toMap();
        ret.put("time", TimeOperator.timetamp2time(time));
        ret.put("user", user);
        return ret;
    }
    
    public static WeworkConverse conFactory(String data,FileStorer fs){
        Map<String,String>map=StringOperator.objectFromString(data);
        return new SwitchConverse(Long.parseLong(map.get("time")),StringOperator.value2String(map.get("user")));
    }
}
