package com.ocrown;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import javax.crypto.Cipher;

import com.ocrown.exception.GetChatDataFailException;
import com.ocrown.exception.GetChatDateErrorException;
import com.ocrown.exception.SdkInitFailException;
import com.tencent.wework.Finance;

import net.iharder.Base64;

public class DataFetcher {
    String corpid;
    String secret;
    String proxy;
    String passwd;
    String privatekey;
    long sdk;
    
    public DataFetcher(String corpid,String secret,String proxy,String passwd,String privatekey) throws SdkInitFailException {
        this.corpid=corpid;
        this.secret=secret;
        this.proxy=proxy;
        this.passwd=passwd;
        this.privatekey=privatekey;
        sdk=Finance.NewSdk();
        long ret=Finance.Init(sdk, corpid, secret);
        if(ret != 0){
            Finance.DestroySdk(sdk);
            throw new SdkInitFailException(ret);
        }
    }
    public DataFetcher(String corpid,String secret,String privatekey) throws SdkInitFailException {
        this.corpid=corpid;
        this.secret=secret;
        this.proxy="";
        this.passwd="";
        this.privatekey=privatekey;
        sdk=Finance.NewSdk();
        long ret=Finance.Init(sdk, corpid, secret);
        if(ret != 0){
            Finance.DestroySdk(sdk);
            throw new SdkInitFailException(ret);
        }
    }
    @Override
    protected void finalize() throws Throwable {
        Finance.DestroySdk(sdk);
        super.finalize();
    }
    public void setProxy(String proxy) {
        this.proxy = proxy;
    }
    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
    public Vector<String>fetchChatData() throws Exception {
        long beginseq=0;
        int limit=1000;
        int timeout=5;
        long slice=Finance.NewSlice();
        long ret;
        boolean run=true;
        Vector<String>chatdatal3=new Vector<>();
        while(run){
            ret = Finance.GetChatData(sdk, beginseq, limit, proxy, passwd, timeout, slice);
            if(ret!=0){
                Finance.FreeSlice(slice);
                throw new GetChatDataFailException(ret);
            }
            String chatdatal0=Finance.GetContentFromSlice(slice);
            Finance.FreeSlice(slice);
            Vector<String>l0keys=new Vector<>();
            l0keys.add("errcode");l0keys.add("errmsg");l0keys.add("chatdata");
            Map<String,String>mapl0= StringOperator.objectFromString(chatdatal0, l0keys);
            if(Integer.parseInt(mapl0.get("errcode"))!=0){
                throw new GetChatDateErrorException(Integer.parseInt((mapl0.get("errcode"))), mapl0.get("errormsg"));
            }
            
            String chatdatal1=mapl0.get("chatdata");
            //System.out.println(chatdatal1);
            Vector<String>l1list=StringOperator.listFromString(chatdatal1);
            //System.out.println(l1list.get(0));
            if(l1list.size()==0){
                run=false;
                continue;
            }
            Vector<String> chatdatal3part=new Vector<>();
            long maxseq=0;
            for(Iterator<String>i=l1list.iterator();i.hasNext();){
                String chatdatal2=i.next();
                Vector<String>l2keys=new Vector<>();
                l2keys.add("seq");l2keys.add("encrypt_random_key");l2keys.add("encrypt_chat_msg");
                Map<String,String>mapl2=StringOperator.objectFromString(chatdatal2, l2keys);
                int seq=Integer.parseInt(mapl2.get("seq"));
                String enrankey=mapl2.get("encrypt_random_key");
                enrankey=enrankey.substring(1,enrankey.length()-1);
                String enchatmsg=mapl2.get("encrypt_chat_msg");
                enchatmsg=enchatmsg.substring(1,enchatmsg.length()-1);
                String randkey=RsaDecrypt(enrankey, privatekey);
                long chatmsg=Finance.NewSlice();
                //System.out.println(randkey);
                //System.out.println(enchatmsg);
                Finance.DecryptData(sdk, randkey, enchatmsg, chatmsg);
                chatdatal3part.add(Finance.GetContentFromSlice(chatmsg));
                Finance.FreeSlice(chatmsg);
                if(seq>maxseq)maxseq=seq;
            }
            chatdatal3.addAll(chatdatal3part);
            beginseq=maxseq;
        }
        return chatdatal3;
    }

    String RsaDecrypt(String str, String privateKey) throws Exception {
        // 64位解码加密后的字符串
        byte[] inputByte = Base64.decode(str.getBytes("UTF-8"));

        // base64编码的私钥
        byte[] decoded = Base64.decode(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA")
                .generatePrivate(new PKCS8EncodedKeySpec(decoded));
        // RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }
}
