package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.FileStorer;
import com.ocrown.StringOperator;

public class VoipDocShareMsg extends DownloadableMsg {

    String voipid;
    String filename;

    VoipDocShareMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, int msgtime,
            String msgtype, String sdkfileid, String md5sum, long filesize,String voipid,String filename,FileStorer fs) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype, sdkfileid, md5sum, filesize, fs);
        this.voipid=voipid;
        this.filename=filename;
    }
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
        // TODO Auto-generated method stub
        return super.toMap();
    }
    
    public static ChatMsg msgFactory(String chatdatal3,FileStorer fs){
        Vector<String>keys=new Vector<>();
        keys.add("sdkfileid");keys.add("md5sum");keys.add("filesize");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new ImageMsg(StringOperator.value2String(map.get("sdkfileid")),
        StringOperator.value2String(map.get("md5sum")),Integer.parseInt(map.get("filesize")),fs);
    }
}
