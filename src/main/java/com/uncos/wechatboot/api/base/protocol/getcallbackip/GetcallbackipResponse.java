package com.uncos.wechatboot.api.base.protocol.getcallbackip;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xuwen on 2016/1/15.
 */
public class GetcallbackipResponse {

    private String[] ipList;

    @JsonProperty("ip_list")
    public String[] getIpList() {
        return ipList;
    }

    public void setIpList(String[] ipList) {
        this.ipList = ipList;
    }
}
