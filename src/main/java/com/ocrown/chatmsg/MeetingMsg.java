package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;
import com.ocrown.TimeOperator;

public class MeetingMsg extends ChatMsg {

    String topic;
    long starttime;
    long endtime;
    String address;
    String remarks;
    // 会议消息类型。101发起会议邀请消息、102处理会议邀请消息。
    long meetingtype;
    // 会议id。方便将发起、处理消息进行对照。uint64类型
    long meetingid;
    // 会议邀请处理状态。1 参加会议、2 拒绝会议、3 待定、4 未被邀请、5 会议已取消、6 会议已过期、7 不在房间内。Uint32类型。
    // 只有meetingtype为102的时候此字段才有内容。
    long status;
    
    MeetingMsg(String topic,long starttime,long endtime,String adress,String remarks,long meetingtype,long meetingid,long status){
        super();
        this.topic=topic;
        this.starttime=starttime;
        this.endtime=endtime;
        this.address=adress;
        this.remarks=remarks;
        this.meetingtype=meetingtype;
        this.meetingid=meetingid;
        this.status=status;
    }

    public String getTopic() {
        return topic;
    }
    public long getEndtime() {
        return endtime;
    }
    public long getStarttime() {
        return starttime;
    }
    public String getAddress() {
        return address;
    }
    public String getRemarks() {
        return remarks;
    }
    public long getMeetingtype() {
        return meetingtype;
    }
    public long getMeetingid() {
        return meetingid;
    }
    public long getStatus() {
        return status;
    }

    @Override
    Map<String, String> toMap() {
        Map<String,String> ret=super.toMap();
        ret.put("topic", topic);
        ret.put("starttime", TimeOperator.timetamp2time(starttime));
        ret.put("endtime", TimeOperator.timetamp2time(endtime));
        ret.put("adress", address);
        ret.put("remarks",remarks);
        ret.put("meetingtype", Long.toString(meetingtype));
        ret.put("meetingid", Long.toString(meetingid));
        ret.put("status", Long.toString(status));
        return ret;
    }


    public static ChatMsg msgFactory(String msgid,String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("topic");keys.add("starttime");keys.add("endtime");
        keys.add("adress");keys.add("remarks");keys.add("meetingtype");
        keys.add("meetingid");keys.add("status");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new MeetingMsg(StringOperator.value2String(map.get("topic")),
        Long.parseLong(map.get("starttime")),Long.parseLong(map.get("endtime")),
        StringOperator.value2String(map.get("address")),StringOperator.value2String(map.get("remarks")),
        Long.parseLong(map.get("meetingtype")),Long.parseLong(map.get("meetingid")),Long.parseLong(map.get("status")));
    }
}
