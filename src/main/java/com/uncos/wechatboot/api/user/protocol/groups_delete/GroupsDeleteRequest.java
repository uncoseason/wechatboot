package com.uncos.wechatboot.api.user.protocol.groups_delete;

import com.uncos.wechatboot.base.request.BaseAccessTokenRequest;
import com.uncos.wechatboot.common.Group;

/**
 * Created by xuwen on 2016/1/16.
 */
public class GroupsDeleteRequest extends BaseAccessTokenRequest {

    private Group group;

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
