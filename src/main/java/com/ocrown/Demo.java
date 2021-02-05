package com.ocrown;

import java.util.Iterator;
import java.util.Vector;

import com.tencent.wework.Finance;

public class Demo {
    
    public static void main(String[] args){

        String corpid="ww092ba56a177e8f0f";
        String secret="A2toQDniaSRfoOc0pZLrtNAvW1vaIAUhUaDnQ6IvKDE";

        long ret = 0;
        long sdk = Finance.NewSdk();
        ret = Finance.Init(sdk, corpid, secret);
        
        if(ret != 0){
            Finance.DestroySdk(sdk);
            System.out.println("init sdk err ret "+ret);
            return;
        }
        
        int seq = 0;
        int limit = 1000;
        String proxy = "";
        String passwd = "";
        int timeout = 5;
        
        long slice = Finance.NewSlice();
        ret = Finance.GetChatData(sdk, seq, limit, proxy, passwd, timeout, slice);
        if (ret != 0) {
            System.out.println("getchatdata ret " + ret);
            Finance.FreeSlice(slice);
            return;
        }
        String chatdata=Finance.GetContentFromSlice(slice);
        //System.out.println("getchatdata :" + chatdata);
        Finance.FreeSlice(slice);        
        int indexerrcode=chatdata.indexOf("\"errcode\"");
        int indexerrmsg=chatdata.indexOf("\"errmsg\"");
        int indexdata=chatdata.indexOf("\"chatdata\"");
        int indexlm=chatdata.indexOf("[");
        int indexrm=chatdata.indexOf("]");
        int errcode=Integer.parseInt(chatdata.substring(indexerrcode+10, indexerrmsg-1));
        //System.out.println("errcode : "+errcode);
        String errmsg=chatdata.substring(indexerrmsg+9, indexdata-1);
        //System.out.println("errmsg : "+errmsg);
        if (errcode!=0){
            System.out.println("errcode "+errcode+"errmsg "+errmsg);
            return;
        }
        chatdata=chatdata.substring(indexlm+1, indexrm);
        //System.out.println("chatdata : "+chatdata);

        Vector<String>msgs=new Vector<String>();
        int n=0;
        while(!chatdata.isEmpty()){
            n++;
            int indexrb=chatdata.indexOf("}");
            String newmsg = chatdata.substring(1, indexrb);
            //System.out.println("newmsg"+n+" : "+newmsg);
            msgs.add(newmsg);
            if(indexrb+1<chatdata.length())
                chatdata = chatdata.substring(indexrb+2,chatdata.length());
            else
                chatdata = "";
            //System.out.println("chatdata : "+chatdata);
        }
        //System.out.println(msgs);
        Vector<PreChatMsg>chatmsgs=new Vector<PreChatMsg>();
        for(Iterator<String>i=msgs.iterator();i.hasNext();){
            String msg=i.next();
            //System.out.println(msg);
            int indexseq=msg.indexOf("seq");
            int indexmi=msg.indexOf("msgid");
            int indexpv=msg.indexOf("publickey_ver");
            int indexerk=msg.indexOf("encrypt_random_key");
            int indexecm=msg.indexOf("encrypt_chat_msg");
            int intseq=Integer.parseInt(msg.substring(indexseq+5, indexmi-2));
            String strmsgid=msg.substring(indexmi+8,indexpv-3);
            int intpbkv=Integer.parseInt(msg.substring(indexpv+15, indexerk-2));
            String strerk=msg.substring(indexerk+21, indexecm-3);
            String strecm=msg.substring(indexecm+19, msg.length()-1);
            //System.out.println(strecm);
            PreChatMsg chatMsg=new PreChatMsg(intseq,strmsgid,intpbkv,strerk,strecm);
            chatmsgs.add(chatMsg);
        }

        for(Iterator<PreChatMsg>i=chatmsgs.iterator();i.hasNext();){
            PreChatMsg cm=i.next();
            cm.setEncrypt_key();
            //System.out.println(cm.encrypt_key);
            System.out.println(cm.encrypt_chat_msg);
            long chatmsg=Finance.NewSlice();
            Finance.DecryptData(sdk, cm.encrypt_key, cm.encrypt_chat_msg, chatmsg);
            System.out.println(Finance.GetContentFromSlice(chatmsg));
            Finance.FreeSlice(chatmsg);
        }
        
        Finance.DestroySdk(sdk);
    }


}
