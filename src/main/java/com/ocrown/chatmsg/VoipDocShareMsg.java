package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.FileStorer;
import com.ocrown.StringOperator;

public class VoipDocShareMsg extends DownloadableMsg {

    String voipid;
    String filename;

    VoipDocShareMsg(String sdkfileid,String md5sum,long filesize, FileStorer fs){
        super(sdkfileid,md5sum,filesize,fs);
    }

    public String getVoipid() {
        return voipid;
    }
    public String getFilename() {
        return filename;
    }
    @Override
    Map<String, String> toMap() {
        Map<String,String>ret= super.toMap();
        String filepath=downloadFile(msgid+".mp3");
        ret.put("filepath", filepath);
        return ret;
    }
    
    public static ChatMsg msgFactory(String msgid,String chatdatal3,FileStorer fs){
        Vector<String>keys=new Vector<>();
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new ImageMsg(StringOperator.value2String(map.get("sdkfileid")),
        StringOperator.value2String(map.get("md5sum")),Integer.parseInt(map.get("filesize")),fs);
    }
}
