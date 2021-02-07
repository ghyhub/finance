package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.FileStorer;
import com.ocrown.StringOperator;

public class VoiceMsg extends DownloadableMsg {

    long  playlength;

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
        String filepath=downloadFile(msgid+".mp3");
        ret.put("play_length", Long.toString(playlength));
        ret.put("filepath", filepath);
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
