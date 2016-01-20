package com.uncos.wechatboot.api;

import com.uncos.wechatboot.api.oauth.protocol.get_access_token.GetAccessTokenRequest;
import com.uncos.wechatboot.api.oauth.protocol.get_access_token.GetAccessTokenResponse;
import com.uncos.wechatboot.api.oauth.protocol.get_userinfo.GetUserinfoRequest;
import com.uncos.wechatboot.api.oauth.protocol.get_userinfo.GetUserinfoResponse;
import com.uncos.wechatboot.api.oauth.protocol.refresh_access_token.RefreshAccessTokenRequest;
import com.uncos.wechatboot.api.oauth.protocol.refresh_access_token.RefreshAccessTokenResponse;
import com.uncos.wechatboot.api.oauth.protocol.valid_access_token.ValidAccessTokenRequest;
import com.uncos.wechatboot.api.oauth.protocol.valid_access_token.ValidAccessTokenResponse;
import com.uncos.wechatboot.common.OAuthScope;
import com.uncos.wechatboot.exception.WechatException;
import com.uncos.wechatboot.utils.Converter;
import org.junit.Test;

/**
 * Created by xuwen on 2016/1/21.
 */
public class OAuthApiTest {

    @Test
    public void testGenerateRedirectURI() {
        System.out.println("测试 生成OAuth重定向URI（用户同意授权，获取code）" + Wechatboot.oAuthApi().buildRedirectUri("http://uncoseason.xicp.net/oauth", OAuthScope.snsapi_userinfo, "test info"));
    }

    @Test
    public void testOAuth() throws WechatException {
        // 这个用例需要重定向URI带回的code参数
        String code = "021b620732dd3897dda62121411b256z";
        GetAccessTokenResponse getAccessTokenResponse = Wechatboot.oAuthApi().getAccessToken(code);
        GetUserinfoResponse getUserinfoResponse = Wechatboot.oAuthApi().getUserinfo(getAccessTokenResponse.getAccessToken(), getAccessTokenResponse.getOpenid());
        ValidAccessTokenResponse validAccessTokenResponse = Wechatboot.oAuthApi().validAccessToken(getAccessTokenResponse.getAccessToken(), getAccessTokenResponse.getOpenid());
        RefreshAccessTokenResponse refreshAccessTokenResponse = Wechatboot.oAuthApi().refreshAccessToken(getAccessTokenResponse.getRefreshToken());
        System.out.println(String.format("测试 通过code换取网页授权access_token\n%s\n" +
                "测试 拉取用户信息(需scope为 snsapi_userinfo)\n%s" +
                "测试 检验授权凭证（access_token）是否有效\n%s\n" +
                "测试 刷新access_token（如果需要）\n%s\n" ,
                Converter.toJSON(getAccessTokenResponse),
                Converter.toJSON(getUserinfoResponse),
                Converter.toJSON(validAccessTokenResponse),
                Converter.toJSON(refreshAccessTokenResponse)
        ));
    }

}
