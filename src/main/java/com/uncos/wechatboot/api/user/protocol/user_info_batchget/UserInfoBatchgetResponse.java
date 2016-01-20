package com.uncos.wechatboot.api.user.protocol.user_info_batchget;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.api.user.protocol.user_info.UserInfoResponse;

import java.util.List;

/**
 * Created by xuwen on 2016/1/17.
 */
public class UserInfoBatchgetResponse {

    private List<UserInfoResponse> userInfoList;

    @JsonProperty("user_info_list")
    public List<UserInfoResponse> getUserInfoList() {
        return userInfoList;
    }

    public void setUserInfoList(List<UserInfoResponse> userInfoList) {
        this.userInfoList = userInfoList;
    }
}
