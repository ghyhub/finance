package com.ocrown.exception;

public class SdkInitFailException extends Exception{

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    
    long ret;

    public SdkInitFailException(long ret){
        this.ret=ret;
    }

    public void setRet(long ret) {
        this.ret = ret;
    }
    public long getRet() {
        return ret;
    }
}
