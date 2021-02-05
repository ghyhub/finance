package com.ocrown;

import javax.crypto.Cipher;

import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;
import net.iharder.Base64;

public class PreChatMsg {
    int seq;
    String msgid;
    int publickey_ver;
    String encrypt_random_key;
    String encrypt_key;
    String encrypt_chat_msg;
    long chat_msg;
    String private_key=
            
            "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALGM49Ap6xneRda7"
            + "42A3uUiSIxvdFvyVfECEda7t5GWTVb1kxOnzKP6s6M8rBbpnD5WzfAlpMA+7edI1"
            + "Kt74bP0QEHDv/T7gX5+NGo2s+bDGjyW1qnFAHPwY2Ana6Ev0Yk9lggfRwRPMK2iR"
            + "376cqEWbohfMopuv/6Myx2oXAriLAgMBAAECgYAulubMn8NGVXIEB+eBPv4KkfZO"
            + "gHRgsSUalOeAkIIHOWMdQIz/SsQuF9c7TXHCWPrY8HES0xw+dJZh13u5aHzIdqDW"
            + "8LlHBHD1uFpsU08r0pMhFU/K/KODtFyy5uzCUB/5lundssLQ6O/r5e8MTOPy+cF2"
            + "T0JhEMjESA2zZIIKgQJBAOe5CXFI7AKLIP/9P398lGfet9vrEoXDHdMknGqIrxVB"
            + "MequaQtC34qr4kqZLq7ke7zl+QVVNOkuRCvdR2GJHssCQQDEJuk8VoOUKHNljzST"
            + "w+SvF0TVC5kx6RWbRr0STAiiqCc3lA+5MRzfhNdlaAXu+1TWleQlPVlkMtDo9q2Y"
            + "WdVBAkAIb7IqT69YAFy5NS0kpRJ6HZ+wAATyncHuN/0B2+wT6AW7N5JXeJ3O3Kcm"
            + "dCFA+MyQegbw7MM6YhbVSf8ZS/a1AkBWN1wxHz26c4U1UJqlkGcF5s5kbjKp02+h"
            + "zpfwYOcyx4JlVXkLx2CPho4+jlh9UI9buoqLK8oM1qEopLRs0J+BAkEAlPukhTXY"
            + "QLr3ArMUpqOvs6RSKz1f1+t3H7hPg5P+6Am+3/WN34PKrPcTQsS5llPOGTrW5aH+" + "tqmpw0WMw5np2Q==";
    

    PreChatMsg(int seq, String msgid, int publickey_ver, String encrypt_random_key, String encrypt_chat_msg) {
        this.seq = seq;
        this.msgid = msgid;
        this.publickey_ver = publickey_ver;
        this.encrypt_random_key = encrypt_random_key;
        this.encrypt_chat_msg = encrypt_chat_msg;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getSeq() {
        return seq;
    }

    public void setMsgid(String msgid) {
        this.msgid = msgid;
    }

    public String getMsgid() {
        return msgid;
    }

    public void setPublickey_ver(int publickey_ver) {
        this.publickey_ver = publickey_ver;
    }

    public int getPublickey_ver() {
        return publickey_ver;
    }

    public void setEncrypt_chat_msg(String encrypt_chat_msg) {
        this.encrypt_chat_msg = encrypt_chat_msg;
    }

    public String getEncrypt_chat_msg() {
        return encrypt_chat_msg;
    }

    public String getEncrypt_key() {
        return encrypt_key;
    }

    public void setChat_msg(long chat_msg) {
        this.chat_msg = chat_msg;
    }

    public long getChat_msg() {
        return chat_msg;
    }

    public static String decrypt(String str, String privateKey) throws Exception {
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

    public void setEncrypt_key() {
        try {
            encrypt_key = decrypt(encrypt_random_key, private_key);
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
            return;
        }
    }
}
