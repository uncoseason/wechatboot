package com.uncos.wechatboot.common;

/**
 * 支付的结果
 * 对应以下2项返回数据
 * return_code
 * result_code
 * <p/>
 * Created by xuwen on 2016/1/19.
 */
public enum PayCode {

    SUCCESS,
    FAIL;

    public boolean equals(String payCode) {
        return this.toString().equals(payCode);
    }

}
