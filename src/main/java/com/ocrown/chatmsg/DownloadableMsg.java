package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.FileStorer;
import com.ocrown.StringOperator;

public abstract class DownloadableMsg extends ChatMsg {

    String sdkfileid;
    String md5sum;
    long filesize;
    FileStorer fs;

    DownloadableMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, int msgtime,String msgtype,
            String sdkfileid, String md5sum, long filesize,FileStorer fs) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.fs=fs;
        this.sdkfileid=sdkfileid;
        this.md5sum=md5sum;
        this.filesize=filesize;
    }

    DownloadableMsg(String sdkfileid,String md5sum,long filesize,FileStorer fs){
        super();
        this.sdkfileid=sdkfileid;
        this.md5sum=md5sum;
        this.filesize=filesize;
        this.fs=fs;
    }

    public String getSdkfileid() {
        return sdkfileid;
    }
    public String getMd5sum() {
        return md5sum;
    }
    public long getFilesize() {
        return filesize;
    }
    public String downloadFile(String name){
        return fs.saveFile(name, sdkfileid);
    }

    @Override
    Map<String, String> toMap() {
        return super.toMap();
    }
}
