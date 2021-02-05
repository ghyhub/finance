package com.ocrown.chatmsg;

public class SwitchMsg {
    
    String msgid;
    String action;
    long time;
    String user;
    SwitchMsg(String msgid,String action,long time,String user){
        this.msgid=msgid;
        this.action=action;
        this.time=time;
        this.user=user;
    }
}
