package com.uncos.wechatboot.api.user.protocol.user_info_batchget;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.api.user.protocol.user_info.UserInfoRequest;
import com.uncos.wechatboot.base.request.BaseAccessTokenRequest;

import java.util.List;

/**
 * Created by xuwen on 2016/1/17.
 */
public class UserInfoBatchgetRequest extends BaseAccessTokenRequest {

    private List<UserInfoRequest> userList;

    @JsonProperty("user_list")
    public List<UserInfoRequest> getUserList() {
        return userList;
    }

    public void setUserList(List<UserInfoRequest> userList) {
        this.userList = userList;
    }
}
