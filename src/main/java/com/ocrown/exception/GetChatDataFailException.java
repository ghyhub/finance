package com.ocrown.exception;

public class GetChatDataFailException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    long ret;

    public GetChatDataFailException(long ret) {
        this.ret=ret;
    }

    public void setRet(long ret) {
        this.ret = ret;
    }
    public long getRet() {
        return ret;
    }
}
