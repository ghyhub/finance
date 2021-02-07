package com.ocrown.chatmsg;

import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import com.ocrown.DataBase;
import com.ocrown.FileStorer;
import com.ocrown.StringOperator;
import com.ocrown.TimeOperator;

public class MeetingVoiceCall extends ChatMsg {
    String voiceid;
    long endtime;
    String sdkfileid;
    FileStorer fs;
    Vector<MeetingDemofiledata> demofiledatas;
    Vector<MeetingSharescreendata> sharesreendatas;

    MeetingVoiceCall() {

    }

    MeetingVoiceCall(String voiceid, long endtime, String sdkfileid, Vector<MeetingDemofiledata> demofiledatas,
            Vector<MeetingSharescreendata> sharescreendatas) {
        super();
        this.voiceid = voiceid;
        this.endtime = endtime;
        this.sdkfileid = sdkfileid;
        this.demofiledatas = demofiledatas;
        this.sharesreendatas = sharescreendatas;
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
        Map<String,String>ret= super.toMap();
        ret.put("voiceid", voiceid);
        ret.put("endtime", TimeOperator.timetamp2time(endtime));
        String filepath=downloadFile(msgid+".jpg");
        ret.put("filepath", filepath);
        return ret;
    }

    public String downloadFile(String name){
        return fs.saveFile(name,sdkfileid);
    }
    @Override
    public void saveMsg(DataBase db, String table) throws SQLException {
        super.saveMsg(db, table);
        for(MeetingDemofiledata mdfd:demofiledatas){
            mdfd.saveMsg(db, "meetingdemofiledatatable");
        }
        for(MeetingSharescreendata mssd:sharesreendatas){
            mssd.saveMsg(db, "meetingsharescreendatatable");
        }
    }

    public static ChatMsg msgFactory(String msgid, String chatdatal3, Object fs) {
        Map<String, String> map = StringOperator.objectFromString(chatdatal3);
        Vector<String> demofiledatastr = StringOperator.listFromString(map.get("demofiledata"));
        Vector<String> sharesreendatastr = StringOperator.listFromString(map.get("sharescreen"));
        Vector<MeetingDemofiledata> filedata = new Vector<>();
        Vector<MeetingSharescreendata> sharescreendatas = new Vector<>();
        for (int i = 0; i < demofiledatastr.size(); i++) {
            filedata.add(new MeetingDemofiledata(msgid, i, demofiledatastr.get(i)));
        }
        for (int i = 0; i < sharesreendatastr.size(); i++) {
            sharescreendatas.add(new MeetingSharescreendata(msgid, i, sharesreendatastr.get(i)));
        }
        return new MeetingVoiceCall(StringOperator.value2String(map.get("voiceid")), Long.parseLong(map.get("endtime")),
                StringOperator.value2String(map.get("sdkfileid")), filedata, sharescreendatas);
    }
}
