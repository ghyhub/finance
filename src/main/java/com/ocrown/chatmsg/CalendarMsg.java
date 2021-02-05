package com.ocrown.chatmsg;

import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;
import com.ocrown.TimeOperator;

public class CalendarMsg extends ChatMsg {

    String title;
    String creatorname;
    Vector<String> attendeename;
    long starttime;
    long endtime;
    String place;
    String remarks;

    CalendarMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, long msgtime,
            String msgtype, String title, String creatorname, Vector<String> attendeename, long starttime, long endtime,
            String place, String remarks) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.title = title;
        this.creatorname = creatorname;
        this.attendeename = attendeename;
        this.starttime = starttime;
        this.endtime = endtime;
        this.place = place;
        this.remarks = remarks;
    }

    CalendarMsg(String title, String creatorname, Vector<String> attendeename, long starttime, long endtime,
            String place, String remarks) {
        super();
        this.title = title;
        this.creatorname = creatorname;
        this.attendeename = attendeename;
        this.starttime = starttime;
        this.endtime = endtime;
        this.place = place;
        this.remarks = remarks;
    }

    public String getTitle() {
        return title;
    }

    public String getCreatorname() {
        return creatorname;
    }

    public Vector<String> getAttendeename() {
        return attendeename;
    }

    public long getStarttime() {
        return starttime;
    }

    public long getEndtime() {
        return endtime;
    }

    public String getPlace() {
        return place;
    }

    public String getRemarks() {
        return remarks;
    }

    @Override
    Map<String, String> toMap() {
        Map<String, String> ret = super.toMap();
        ret.put("title", title);
        ret.put("creator", creatorname);
        String attendeelist = "[";
        for (Iterator<String> i = attendeename.iterator(); i.hasNext();) {
            attendeelist = attendeelist + i.next();
            if (i.hasNext()) {
                attendeelist = attendeelist + ",";
            } else {
                attendeelist = attendeelist + "]";
            }
        }
        ret.put("attendeelist", attendeelist);
        ret.put("starttime", TimeOperator.timetamp2time(starttime));
        ret.put("endtime", TimeOperator.timetamp2time(endtime));
        ret.put("place", place);
        ret.put("remarks", remarks);
        return ret;
    }

    public static ChatMsg msgFactory(String msgid,String chatdatal3, Object fs) {
        Vector<String> keys = new Vector<>();
        keys.add("title");
        keys.add("creator");
        keys.add("attendeelist");
        keys.add("starttime");
        keys.add("endtime");
        keys.add("place");
        keys.add("remarks");
        Map<String, String> map = StringOperator.objectFromString(chatdatal3, keys);
        return new CalendarMsg(StringOperator.value2String(map.get("title")),
                StringOperator.value2String(map.get("creator")),
                StringOperator.list2StringList(StringOperator.listFromString(map.get("attendeelist"))),
                Long.parseLong(map.get("starttime")), Long.parseLong(map.get("endtime")),
                StringOperator.value2String(map.get("place")), StringOperator.value2String(map.get("remarks")));
    }
}
