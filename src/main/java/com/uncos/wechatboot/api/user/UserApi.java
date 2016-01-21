package com.uncos.wechatboot.api.user;

import com.uncos.wechatboot.api.user.protocol.groups_create.GroupsCreateRequest;
import com.uncos.wechatboot.api.user.protocol.groups_create.GroupsCreateResponse;
import com.uncos.wechatboot.api.user.protocol.groups_delete.GroupsDeleteRequest;
import com.uncos.wechatboot.api.user.protocol.groups_delete.GroupsDeleteResponse;
import com.uncos.wechatboot.api.user.protocol.groups_get.GroupsGetRequest;
import com.uncos.wechatboot.api.user.protocol.groups_get.GroupsGetResponse;
import com.uncos.wechatboot.api.user.protocol.groups_getid.GroupsGetidRequest;
import com.uncos.wechatboot.api.user.protocol.groups_getid.GroupsGetidResponse;
import com.uncos.wechatboot.api.user.protocol.groups_members_batchupdate.GroupsMembersBatchupdateRequest;
import com.uncos.wechatboot.api.user.protocol.groups_members_batchupdate.GroupsMembersBatchupdateResponse;
import com.uncos.wechatboot.api.user.protocol.groups_members_update.GroupsMembersUpdateRequest;
import com.uncos.wechatboot.api.user.protocol.groups_members_update.GroupsMembersUpdateResponse;
import com.uncos.wechatboot.api.user.protocol.groups_update.GroupsUpdateRequest;
import com.uncos.wechatboot.api.user.protocol.groups_update.GroupsUpdateResponse;
import com.uncos.wechatboot.api.user.protocol.user_get.UserGetRequest;
import com.uncos.wechatboot.api.user.protocol.user_get.UserGetResponse;
import com.uncos.wechatboot.api.user.protocol.user_info.UserInfoRequest;
import com.uncos.wechatboot.api.user.protocol.user_info.UserInfoResponse;
import com.uncos.wechatboot.api.user.protocol.user_info_batchget.UserInfoBatchgetRequest;
import com.uncos.wechatboot.api.user.protocol.user_info_batchget.UserInfoBatchgetResponse;
import com.uncos.wechatboot.api.user.protocol.user_info_updateremark.UserInfoUpdateremarkRequest;
import com.uncos.wechatboot.api.user.protocol.user_info_updateremark.UserInfoUpdateremarkResponse;
import com.uncos.wechatboot.common.Group;
import com.uncos.wechatboot.common.Lang;
import com.uncos.wechatboot.common.RequestType;
import com.uncos.wechatboot.exception.WechatException;
import com.uncos.wechatboot.utils.Checker;
import com.uncos.wechatboot.utils.Converter;
import com.uncos.wechatboot.utils.CredentialCenter;
import com.uncos.wechatboot.utils.Http;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户管理API
 * Created by xuwen on 2016-1-15.
 */
public class UserApi {

    /*创建分组*/
    private static final String API_GROUPS_CREATE = "https://api.weixin.qq.com/cgi-bin/groups/create?access_token=ACCESS_TOKEN";
    /*查询所有分组*/
    private static final String API_GROUPS_GET = "https://api.weixin.qq.com/cgi-bin/groups/get?access_token=ACCESS_TOKEN";
    /*查询用户所在分组*/
    private static final String API_GROUPS_GETID = "https://api.weixin.qq.com/cgi-bin/groups/getid?access_token=ACCESS_TOKEN";
    /*修改分组名*/
    private static final String API_GROUPS_UPDATE = "https://api.weixin.qq.com/cgi-bin/groups/update?access_token=ACCESS_TOKEN";
    /*移动用户分组*/
    private static final String API_GROUPS_MEMBERS_UPDATE = "https://api.weixin.qq.com/cgi-bin/groups/members/update?access_token=ACCESS_TOKEN";
    /*批量移动用户分组*/
    private static final String API_GROUPS_MEMBERS_BATCHUPDATE = "https://api.weixin.qq.com/cgi-bin/groups/members/batchupdate?access_token=ACCESS_TOKEN";
    /*删除分组*/
    private static final String API_GROUPS_DELETE = "https://api.weixin.qq.com/cgi-bin/groups/delete?access_token=ACCESS_TOKEN";
    /*设置用户备注名*/
    private static final String API_USER_INFO_UPDATEREMARK = "https://api.weixin.qq.com/cgi-bin/user/info/updateremark?access_token=ACCESS_TOKEN";
    /*获取用户基本信息(UnionID机制)*/
    private static final String API_USER_INFO = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    /*批量获取用户基本信息*/
    private static final String API_USER_INFO_BATCHGET = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token=ACCESS_TOKEN";
    /*获取用户列表*/
    private static final String API_USER_GET = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID";

    private static UserApi instance = new UserApi();

    public static UserApi instance() {
        return instance;
    }

    /**
     * 创建分组
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public GroupsCreateResponse groupsCreate(GroupsCreateRequest request) throws WechatException {
        String result = Http.post(CredentialCenter.writeAccessTokenURL(API_GROUPS_CREATE), request, RequestType.JSON);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, GroupsCreateResponse.class);
    }

    /**
     * 创建分组
     *
     * @param name 分组名字
     * @return
     * @throws WechatException
     */
    public GroupsCreateResponse groupsCreate(String name) throws WechatException {
        GroupsCreateRequest request = new GroupsCreateRequest();
        Group group = new Group();
        group.setName(name);
        request.setGroup(group);
        return groupsCreate(request);
    }

    /**
     * 查询所有分组
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public GroupsGetResponse groupsGet(GroupsGetRequest request) throws WechatException {
        String result = Http.post(CredentialCenter.writeAccessTokenURL(API_GROUPS_GET), request, RequestType.JSON);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, GroupsGetResponse.class);
    }

    /**
     * 查询所有分组
     *
     * @return
     * @throws WechatException
     */
    public GroupsGetResponse groupsGet() throws WechatException {
        GroupsGetRequest request = new GroupsGetRequest();
        return groupsGet(request);
    }

    /**
     * 查询用户所在分组
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public GroupsGetidResponse groupsGetid(GroupsGetidRequest request) throws WechatException {
        String result = Http.post(CredentialCenter.writeAccessTokenURL(API_GROUPS_GETID), request, RequestType.JSON);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, GroupsGetidResponse.class);
    }

    /**
     * 查询用户所在分组
     *
     * @param openid
     * @return
     * @throws WechatException
     */
    public GroupsGetidResponse groupsGetid(String openid) throws WechatException {
        GroupsGetidRequest request = new GroupsGetidRequest();
        request.setOpenid(openid);
        return groupsGetid(request);
    }

    /**
     * 修改分组名
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public GroupsUpdateResponse groupsUpdate(GroupsUpdateRequest request) throws WechatException {
        String result = Http.post(CredentialCenter.writeAccessTokenURL(API_GROUPS_UPDATE), request, RequestType.JSON);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, GroupsUpdateResponse.class);
    }

    /**
     * 修改分组名
     *
     * @param id   分组id
     * @param name 分组名
     * @return
     * @throws WechatException
     */
    public GroupsUpdateResponse groupsUpdate(int id, String name) throws WechatException {
        GroupsUpdateRequest request = new GroupsUpdateRequest();
        Group group = new Group();
        group.setId(id);
        group.setName(name);
        request.setGroup(group);
        return groupsUpdate(request);
    }

    /**
     * 移动用户分组
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public GroupsMembersUpdateResponse groupsMembersUpdate(GroupsMembersUpdateRequest request) throws WechatException {
        String result = Http.post(CredentialCenter.writeAccessTokenURL(API_GROUPS_MEMBERS_UPDATE), request, RequestType.JSON);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, GroupsMembersUpdateResponse.class);
    }

    /**
     * 移动用户分组
     *
     * @param openid
     * @param toGroupid 分组id
     * @return
     * @throws WechatException
     */
    public GroupsMembersUpdateResponse groupsMembersUpdate(String openid, int toGroupid) throws WechatException {
        GroupsMembersUpdateRequest request = new GroupsMembersUpdateRequest();
        request.setOpenid(openid);
        request.setToGroupid(toGroupid);
        return groupsMembersUpdate(request);
    }

    /**
     * 批量移动用户分组
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public GroupsMembersBatchupdateResponse groupsMembersBatchupdate(GroupsMembersBatchupdateRequest request) throws WechatException {
        String result = Http.post(CredentialCenter.writeAccessTokenURL(API_GROUPS_MEMBERS_BATCHUPDATE), request, RequestType.JSON);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, GroupsMembersBatchupdateResponse.class);
    }

    /**
     * 批量移动用户分组
     *
     * @param openidList 用户id集合
     * @param toGroupid  分组id
     * @return
     * @throws WechatException
     */
    public GroupsMembersBatchupdateResponse groupsMembersBatchupdate(List<String> openidList, int toGroupid) throws WechatException {
        GroupsMembersBatchupdateRequest request = new GroupsMembersBatchupdateRequest();
        request.setOpenidList(openidList);
        request.setToGroupid(toGroupid);
        return groupsMembersBatchupdate(request);
    }

    /**
     * 删除分组
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public GroupsDeleteResponse groupsDelete(GroupsDeleteRequest request) throws WechatException {
        String result = Http.post(CredentialCenter.writeAccessTokenURL(API_GROUPS_DELETE), request, RequestType.JSON);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, GroupsDeleteResponse.class);
    }

    /**
     * 删除分组
     *
     * @param id 分组id
     * @return
     * @throws WechatException
     */
    public GroupsDeleteResponse groupsDelete(int id) throws WechatException {
        GroupsDeleteRequest request = new GroupsDeleteRequest();
        Group group = new Group();
        group.setId(id);
        request.setGroup(group);
        return groupsDelete(request);
    }

    /**
     * 设置用户备注名
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public UserInfoUpdateremarkResponse userInfoUpdateremark(UserInfoUpdateremarkRequest request) throws WechatException {
        String result = Http.post(CredentialCenter.writeAccessTokenURL(API_USER_INFO_UPDATEREMARK), request, RequestType.JSON);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, UserInfoUpdateremarkResponse.class);
    }

    /**
     * 设置用户备注名
     *
     * @param openid 用户标识
     * @param remark 新的备注名，长度必须小于30字符
     * @return
     * @throws WechatException
     */
    public UserInfoUpdateremarkResponse userInfoUpdateremark(String openid, String remark) throws WechatException {
        UserInfoUpdateremarkRequest request = new UserInfoUpdateremarkRequest();
        request.setOpenid(openid);
        request.setRemark(remark);
        return userInfoUpdateremark(request);
    }

    /**
     * 获取用户基本信息（包括UnionID机制）
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public UserInfoResponse userInfo(UserInfoRequest request) throws WechatException {
        String result = Http.get(API_USER_INFO, request);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, UserInfoResponse.class);
    }

    /**
     * 获取用户基本信息（包括UnionID机制）
     *
     * @param lang   国家地区语言版本
     * @param openid 用户标识
     * @return
     * @throws WechatException
     */
    public UserInfoResponse userInfo(Lang lang, String openid) throws WechatException {
        UserInfoRequest request = new UserInfoRequest();
        request.setOpenid(openid);
        request.setLang(lang);
        return userInfo(request);
    }

    /**
     * 获取用户基本信息（包括UnionID机制）
     *
     * @param openid 用户标识
     * @return
     * @throws WechatException
     */
    public UserInfoResponse userInfo(String openid) throws WechatException {
        return userInfo(Lang.zh_CN, openid);
    }

    /**
     * 批量获取用户基本信息
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public UserInfoBatchgetResponse userInfoBatchget(UserInfoBatchgetRequest request) throws WechatException {
        String result = Http.post(CredentialCenter.writeAccessTokenURL(API_USER_INFO_BATCHGET), request, RequestType.JSON);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, UserInfoBatchgetResponse.class);
    }

    /**
     * 批量获取用户基本信息
     *
     * @param lang    国家地区语言版本
     * @param openids 用户标识
     * @return
     * @throws WechatException
     */
    public UserInfoBatchgetResponse userInfoBatchget(Lang lang, String... openids) throws WechatException {
        UserInfoBatchgetRequest request = new UserInfoBatchgetRequest();
        List<UserInfoRequest> userList = new ArrayList<UserInfoRequest>(openids.length);
        for (String openid : openids) {
            UserInfoRequest userInfoRequest = new UserInfoRequest();
            userInfoRequest.setLang(lang);
            userInfoRequest.setOpenid(openid);
            userList.add(userInfoRequest);
        }
        request.setUserList(userList);
        return userInfoBatchget(request);
    }

    /**
     * 批量获取用户基本信息
     *
     * @param openids 用户标识
     * @return
     * @throws WechatException
     */
    public UserInfoBatchgetResponse userInfoBatchget(String... openids) throws WechatException {
        return userInfoBatchget(Lang.zh_CN, openids);
    }

    /**
     * 获取用户列表
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public UserGetResponse userGet(UserGetRequest request) throws WechatException {
        String result = Http.get(API_USER_GET, request);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, UserGetResponse.class);
    }

    /**
     * 获取用户列表
     *
     * @param nextOpenid 第一个拉取的OPENID，不填默认从头开始拉取
     * @return
     * @throws WechatException
     */
    public UserGetResponse userGet(String nextOpenid) throws WechatException {
        UserGetRequest request = new UserGetRequest();
        if (StringUtils.isNotBlank(nextOpenid)) {
            request.setNextOpenid(nextOpenid);
        }
        return userGet(request);
    }

    /**
     * 获取用户列表
     *
     * @return
     * @throws WechatException
     */
    public UserGetResponse userGet() throws WechatException {
        String nextOpenid = null;
        return userGet(nextOpenid);
    }
}
