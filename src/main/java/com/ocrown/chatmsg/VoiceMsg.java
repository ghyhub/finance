package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.FileStorer;
import com.ocrown.StringOperator;

public class VoiceMsg extends DownloadableMsg {

    long  playlength;
    VoiceMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, int msgtime,
            String msgtype, String sdkfileid, String md5sum, long filesize,long playlength,FileStorer fs) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype, sdkfileid, md5sum, filesize,fs);
        this.playlength=playlength;
    }

    VoiceMsg(String sdkfileid,String md5sum,long filesize,long playlength,FileStorer fs){
        super(sdkfileid, md5sum, filesize, fs);
        this.playlength=playlength;
    }

    public long getPlaylength() {
        return playlength;
    }

    @Override
    Map<String, String> toMap() {
        Map<String,String>ret=super.toMap();
        ret.put("play_length", Long.toString(playlength));
        return ret;
    }

    public static ChatMsg msgFactory(String msgid,String chatdatal3,FileStorer fs){
        Vector<String>keys=new Vector<>();
        keys.add("sdkfileid");keys.add("md5sum");keys.add("filesize");
        keys.add("play_length");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new VoiceMsg(StringOperator.value2String(map.get("sdkfileid")),
        StringOperator.value2String(map.get("md5sum")),Long.parseLong(map.get("filesize")),
        Long.parseLong(map.get("play_length")),fs);
    }
    
}
