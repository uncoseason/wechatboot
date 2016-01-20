package com.uncos.wechatboot.api;

import com.uncos.wechatboot.api.user.protocol.groups_create.GroupsCreateResponse;
import com.uncos.wechatboot.api.user.protocol.user_info_batchget.UserInfoBatchgetResponse;
import com.uncos.wechatboot.exception.WechatException;
import com.uncos.wechatboot.utils.RandomStringGenerator;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by xuwen on 2016-1-15.
 */
public class UserApiTest {

    @Test
    public void testGroupsCreate() throws WechatException {
        Wechatboot.userApi().groupsCreate("分组" + RandomStringGenerator.generate(10));
        System.out.println("测试 创建分组");
    }

    @Test
    public void testGroupsGet() throws WechatException {
        Wechatboot.userApi().groupsGet();
        System.out.println("测试 查询所有分组");
    }

    @Test
    public void testGroupsGetid() throws WechatException {
        Wechatboot.userApi().groupsGetid("ouP0Yv3rPy5xPnzLD45g3psCJqZQ");
        System.out.println("测试 查询用户所在分组");
    }

    @Test
    public void testGroupsUpdate() throws WechatException {
        GroupsCreateResponse groupsCreateResponse = Wechatboot.userApi().groupsCreate("分组" + RandomStringGenerator.generate(10));
        Wechatboot.userApi().groupsUpdate(groupsCreateResponse.getGroup().getId(), "修改分组" + RandomStringGenerator.generate(10));
        System.out.println("测试 修改分组名");
    }

    @Test
    public void testGroupsMembersUpdate() throws WechatException {
        GroupsCreateResponse groupsCreateResponse = Wechatboot.userApi().groupsCreate("分组" + RandomStringGenerator.generate(10));
        Wechatboot.userApi().groupsMembersUpdate("ouP0Yv3rPy5xPnzLD45g3psCJqZQ", groupsCreateResponse.getGroup().getId());
        System.out.println("测试 移动用户分组");
    }

    @Test
    public void testGroupsMembersBatchupdate() throws WechatException {
        GroupsCreateResponse groupsCreateResponse = Wechatboot.userApi().groupsCreate("分组" + RandomStringGenerator.generate(10));
        List<String> openidList = new ArrayList<>();
        openidList.add("ouP0Yv3rPy5xPnzLD45g3psCJqZQ");
        Wechatboot.userApi().groupsMembersBatchupdate(openidList, groupsCreateResponse.getGroup().getId());
        System.out.println("测试 批量移动用户分组");
    }

    @Test
    public void testGroupsDelete() throws WechatException {
        GroupsCreateResponse groupsCreateResponse = Wechatboot.userApi().groupsCreate("分组" + RandomStringGenerator.generate(10));
        Wechatboot.userApi().groupsDelete(groupsCreateResponse.getGroup().getId());
        System.out.println("测试 删除分组");
    }

    @Test
    public void testUserInfoUpdateremark() throws WechatException {
        Wechatboot.userApi().userInfoUpdateremark("ouP0Yv3rPy5xPnzLD45g3psCJqZQ", "备注" + RandomStringGenerator.generate(10));
        System.out.println("测试 设置用户备注名");
    }

    @Test
    public void testUserInfo() throws WechatException {
        Wechatboot.userApi().userInfo("ouP0Yv3rPy5xPnzLD45g3psCJqZQ");
        System.out.println("测试 获取用户基本信息（包括UnionID机制）");
    }

    @Test
    public void testUserInfoBatchget() throws WechatException {
        UserInfoBatchgetResponse res = Wechatboot.userApi().userInfoBatchget("ouP0Yv3rPy5xPnzLD45g3psCJqZQ");
        System.out.println("测试 批量获取用户基本信息" + Arrays.toString(res.getUserInfoList().toArray()));
    }

    @Test
    public void testUserget() throws WechatException {
        Wechatboot.userApi().userGet();
        Wechatboot.userApi().userGet("ouP0Yv3rPy5xPnzLD45g3psCJqZQ");
        System.out.println("测试 获取用户列表");
    }
}
