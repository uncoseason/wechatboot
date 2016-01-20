package com.uncos.wechatboot.exception;

/**
 * 加密解密异常
 * Created by xuwen on 2016/1/19.
 */
public class MessageCryptException extends RuntimeException {

    public MessageCryptException() {
    }

    public MessageCryptException(String message) {
        super(message);
    }
}
