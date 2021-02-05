package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.FileStorer;
import com.ocrown.StringOperator;

public class EmotionMsg extends DownloadableMsg {

    long type;
    long width;
    long height;
    EmotionMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, int msgtime,
            String msgtype, String sdkfileid, String md5sum, long filesize, long type,long width,long height,FileStorer fs) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype, sdkfileid, md5sum, filesize,fs);
        this.type=type;
        this.width=width;
        this.height=height;
    }
    EmotionMsg(String sdkfileid,String md5sum,long filesize,FileStorer fs,long type,long width,long height){
        super(sdkfileid, md5sum, filesize,fs);
        this.type=type;
        this.width=width;
        this.height=height;
    }
    
    public long getType() {
        return type;
    }
    public long getWidth() {
        return width;
    }
    public long getHeight() {
        return height;
    }

    @Override
    Map<String, String> toMap() {
        Map<String,String>ret=super.toMap();
        String filename=msgid;
        if(type==1)
            filename=filename+".gif";
        else
            filename=filename+".png";
        String filepath=downloadFile(filename);
        ret.put("filename", filepath);
        return ret;
    }

    public static ChatMsg msgFactory(String msgid,String chatdatal3,FileStorer fs){
        Vector<String>keys=new Vector<>();
        keys.add("sdkfileid");keys.add("md5sum");keys.add("filesize");
        keys.add("type");keys.add("width");keys.add("height");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new EmotionMsg(StringOperator.value2String(map.get("sdkfileid")),
        StringOperator.value2String(map.get("md5sum")),Integer.parseInt(map.get("filesize")),fs,
        Long.parseLong(map.get("type")),Long.parseLong(map.get("width")),Long.parseLong(map.get("height")));
    }
}
