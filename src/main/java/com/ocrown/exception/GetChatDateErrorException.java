package com.ocrown.exception;

public class GetChatDateErrorException extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    long errcode;
    String errmsg;

    public GetChatDateErrorException(long errcode,String errmsg){
        this.errcode=errcode;
        this.errmsg=errmsg;
    }

    public void setErrcode(long errcode) {
        this.errcode = errcode;
    }
    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
    public long getErrcode() {
        return errcode;
    }
    public String getErrmsg() {
        return errmsg;
    }
}
