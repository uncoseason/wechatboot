package com.uncos.wechatboot.api.user.protocol.user_get;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by xuwen on 2016/1/17.
 */
public class UserGetResponse {

    private long total;
    private int count;
    private Data data;
    private String nextOpenid;

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @JsonProperty("next_openid")
    public String getNextOpenid() {
        return nextOpenid;
    }

    public void setNextOpenid(String nextOpenid) {
        this.nextOpenid = nextOpenid;
    }

    public class Data {
        private List<String> openids;

        @JsonProperty("openid")
        public List<String> getOpenids() {
            return openids;
        }

        public void setOpenids(List<String> openids) {
            this.openids = openids;
        }
    }
}
