package com.ocrown.chatmsg;

import java.util.Map;
import java.util.Vector;

import com.ocrown.StringOperator;

public class RedpacketMsg extends ChatMsg {

    //	红包消息类型。1 普通红包、2 拼手气群红包、3 激励群红包
    long type;
    String wish;
    long totalcnt;
    // 红包总金额。Uint32类型，单位为分。
    long totalamount;

    RedpacketMsg(String msgid, String action, String from, Vector<String> tolist, String roomid, long msgtime,
            String msgtype,long type,String wish,long totalcnt,long totalamount) {
        super(msgid, action, from, tolist, roomid, msgtime, msgtype);
        this.type=type;
        this.wish=wish;
        this.totalcnt=totalcnt;
        this.totalamount=totalamount;
    }
    RedpacketMsg(long type,String wish,long totalcnt,long totalamount){
        super();
        this.type=type;
        this.wish=wish;
        this.totalcnt=totalcnt;
        this.totalamount=totalamount;
    }

    public long getType() {
        return type;
    }
    public String getWish() {
        return wish;
    }
    public long getTotalcnt() {
        return totalcnt;
    }
    public long getTotalamount() {
        return totalamount;
    }
    
    @Override
    Map<String, String> toMap() {
        Map<String,String> ret=super.toMap();
        ret.put("type", Long.toString(type));
        ret.put("wish", wish);
        ret.put("totalcnt", Long.toString(totalcnt));
        ret.put("totalamount", Long.toString(totalamount));
        return ret;
    }

    public static ChatMsg msgFactory(String chatdatal3,Object fs){
        Vector<String>keys=new Vector<>();
        keys.add("type");keys.add("wish");keys.add("totalcnt");keys.add("totalamount");
        Map<String,String>map=StringOperator.objectFromString(chatdatal3, keys);
        return new RedpacketMsg(Long.parseLong(map.get("type")),StringOperator.value2String(map.get("wish")),
        Long.parseLong(map.get("totalcnt")),Long.parseLong(map.get("totalamount")));
    }
}
