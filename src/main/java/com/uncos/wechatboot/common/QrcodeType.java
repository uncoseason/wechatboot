package com.uncos.wechatboot.common;

/**
 * 二维码类型
 * Created by xuwen on 2016/1/17.
 */
public enum QrcodeType {
    /**
     * 临时
     */
    QR_SCENE,
    /**
     * 永久
     */
    QR_LIMIT_SCENE,
    /**
     * 永久的字符串参数值
     */
    QR_LIMIT_STR_SCENE,
}
