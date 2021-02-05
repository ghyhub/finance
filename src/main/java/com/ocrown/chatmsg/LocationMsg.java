package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;

public class LocationMsg extends ChatMsg {

    double longitude;
    double latitude;
    String address;
    // 位置信息的title
    String title;
    // 缩放比例
    long zoom;

    LocationMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, int msgtime,
            String msgtype, double longitude, double latitude, String address, String title, long zoom) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.title = title;
        this.zoom = zoom;
    }

    LocationMsg(double longitude, double latitude, String address, String title, long zoom) {
        super();
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
        this.title = title;
        this.zoom = zoom;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public String getAddress() {
        return address;
    }

    public String getTitle() {
        return title;
    }

    public long getZoom() {
        return zoom;
    }

    @Override
    Map<String, String> toMap() {
        Map<String, String> ret = super.toMap();
        ret.put("longitude", Double.toString(longitude));
        ret.put("latitude", Double.toString(latitude));
        ret.put("adress", address);
        ret.put("title", title);
        return ret;
    }

    public static ChatMsg msgFactory(String chatdatal3, Object fs) {
        Vector<String> keys = new Vector<>();
        keys.add("longitude");
        keys.add("latitude");
        keys.add("adress");
        keys.add("title");
        keys.add("zoom");
        Map<String, String> map = StringOperator.objectFromString(chatdatal3, keys);
        return new LocationMsg(Double.parseDouble(map.get("longitude")), Double.parseDouble(map.get("latitude")),
                StringOperator.value2String(map.get("adress")), StringOperator.value2String(map.get("title")),
                Long.parseLong(map.get("zoom")));
    }
}
