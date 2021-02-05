package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.FileStorer;
import com.ocrown.StringOperator;

public class FileMsg extends DownloadableMsg {

    String filename;
    String fileext;
    FileMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, int msgtime, String msgtype,
            String sdkfileid, String md5sum, long filesize,String filename,String fileext,FileStorer fs) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype, sdkfileid, md5sum, filesize,fs);
        this.filename=filename;
        this.fileext=fileext;
    }

    FileMsg(String sdkfileid,String md5sum,long filesize,FileStorer fs,String filename,String fileext){
        super(sdkfileid, md5sum, filesize, fs);
        this.filename=filename;
        this.fileext=fileext;
    }
    
    public String getFilename() {
        return filename;
    }
    public String getFileext() {
        return fileext;
    }
    @Override
    Map<String, String> toMap() {
        Map<String,String> ret=super.toMap();
        String filename=msgid+"__"+this.filename;
        String filepath=downloadFile(filename);
        ret.put("filepath", filepath);
        return ret;
    }
 
    public static ChatMsg msgFactory(String msgid,String chatdatal3,FileStorer fs){
        Vector<String>keys=new Vector<>();
        keys.add("sdkfileid");keys.add("md5sum");keys.add("filesize");
        keys.add("filename");keys.add("fileext");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new FileMsg(StringOperator.value2String(map.get("sdkfileid")),
        StringOperator.value2String(map.get("md5sum")),Integer.parseInt(map.get("filesize")),fs,
        StringOperator.value2String(map.get("filename")),StringOperator.value2String(map.get("fileext")));
    }
    
}
