package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

public class MeetingVoiceCall extends ChatMsg {
    String voiceid;
    long endtime;
    String sdkfileid;
    Vector<MeetingDemofiledata> demofiledatas;
    Vector<MeetingSharescreendata> sharesreendatas;
    
    MeetingVoiceCall(){

    }
    public String getVoiceid() {
        return voiceid;
    }
    public long getEndtime() {
        return endtime;
    }
    public String getSdkfileid() {
        return sdkfileid;
    }
    public Vector<MeetingDemofiledata> getDemofiledatas() {
        return demofiledatas;
    }
    public Vector<MeetingSharescreendata> getSharesreendatas() {
        return sharesreendatas;
    } 
    @Override
    Map<String, String> toMap() {
        return super.toMap();
    }

    public static ChatMsg msgFactory(String msgid,String chatdatal3,Object fs){
        return new MeetingVoiceCall();
    }
}
