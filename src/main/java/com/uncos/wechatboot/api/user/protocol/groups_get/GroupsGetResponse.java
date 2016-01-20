package com.uncos.wechatboot.api.user.protocol.groups_get;

import com.uncos.wechatboot.common.Group;

import java.util.List;

/**
 * Created by xuwen on 2016-1-15.
 */
public class GroupsGetResponse {

    private List<Group> groups;

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }
}
