package com.uncos.wechatboot.exception;

/**
 * 签名异常
 * Created by xuwen on 2016/1/16.
 */
public class SignatureException extends RuntimeException {

    public SignatureException() {
        super("返回结果的签名校验失败，数据可能已经被篡改");
    }

}
