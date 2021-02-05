package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.FileStorer;
import com.ocrown.StringOperator;

public class ImageMsg extends DownloadableMsg {

    ImageMsg(String sdkfileid,String md5sum,long filesize,FileStorer fs){
        super(sdkfileid, md5sum, filesize,fs);
    }

    @Override
    Map<String, String> toMap() {
        Map<String,String> ret=super.toMap();
        String filepath= downloadFile(msgid+".jpg");
        ret.put("filepath", filepath);
        return ret;
    }
    
    public static ChatMsg msgFactory(String msgid,String chatdatal3,FileStorer fs){
        Vector<String>keys=new Vector<>();
        keys.add("sdkfileid");keys.add("md5sum");keys.add("filesize");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new ImageMsg(StringOperator.value2String(map.get("sdkfileid")),
        StringOperator.value2String(map.get("md5sum")),Integer.parseInt(map.get("filesize")),fs);
    }
    
}
