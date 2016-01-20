package com.uncos.wechatboot.api.oauth;

import com.uncos.wechatboot.api.oauth.protocol.get_access_token.GetAccessTokenRequest;
import com.uncos.wechatboot.api.oauth.protocol.get_access_token.GetAccessTokenResponse;
import com.uncos.wechatboot.api.oauth.protocol.get_userinfo.GetUserinfoRequest;
import com.uncos.wechatboot.api.oauth.protocol.get_userinfo.GetUserinfoResponse;
import com.uncos.wechatboot.api.oauth.protocol.redirect_uri.RedirectUriRequest;
import com.uncos.wechatboot.api.oauth.protocol.refresh_access_token.RefreshAccessTokenRequest;
import com.uncos.wechatboot.api.oauth.protocol.refresh_access_token.RefreshAccessTokenResponse;
import com.uncos.wechatboot.api.oauth.protocol.valid_access_token.ValidAccessTokenRequest;
import com.uncos.wechatboot.api.oauth.protocol.valid_access_token.ValidAccessTokenResponse;
import com.uncos.wechatboot.common.OAuthScope;
import com.uncos.wechatboot.common.RequestType;
import com.uncos.wechatboot.exception.WechatException;
import com.uncos.wechatboot.utils.Checker;
import com.uncos.wechatboot.utils.Converter;
import com.uncos.wechatboot.utils.Http;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 网页授权获取用户基本信息
 * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html">开发文档</a></p>
 * Created by xuwen on 2016/1/16.
 */
public class OAuthApi {

    private static Logger logger = LoggerFactory.getLogger(OAuthApi.class);

    /*生成OAuth重定向URI（用户同意授权，获取code）*/
    private static final String HTTPS_OPEN_WEIXIN_QQ_COM_CONNECT_OAUTH2_AUTHORIZE = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URI&response_type=code&scope=SCOPE&state=STATE#wechat_redirect";
    /*通过code换取网页授权access_token*/
    private static final String HTTPS_API_WEIXIN_QQ_COM_SNS_OAUTH2_ACCESS_TOKEN = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
    /*刷新access_token（如果需要）*/
    private static final String HTTPS_API_WEIXIN_QQ_COM_SNS_OAUTH2_REFRESH_TOKEN = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
    /*拉取用户信息(需scope为 snsapi_userinfo)*/
    private static final String HTTPS_API_WEIXIN_QQ_COM_SNS_USERINFO = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
    /*检验授权凭证（access_token）是否有效*/
    private static final String HTTPS_API_WEIXIN_QQ_COM_SNS_AUTH = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";

    private static OAuthApi instance = new OAuthApi();

    public static OAuthApi instance() {
        return instance;
    }

    /**
     * 生成OAuth重定向URI（用户同意授权，获取code）
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E4.B8.80.E6.AD.A5.EF.BC.9A.E7.94.A8.E6.88.B7.E5.90.8C.E6.84.8F.E6.8E.88.E6.9D.83.EF.BC.8C.E8.8E.B7.E5.8F.96code">开发文档</a></p>
     *
     * @param request
     * @return
     */
    public static String buildRedirectUri(RedirectUriRequest request) {
        StringBuffer url = new StringBuffer();
        url.append(Http.buildObjectParamURL(HTTPS_OPEN_WEIXIN_QQ_COM_CONNECT_OAUTH2_AUTHORIZE, request));
        url.append("#wechat_redirect");
        return url.toString();
    }

    /**
     * 生成OAuth重定向URI（用户同意授权，获取code）
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E4.B8.80.E6.AD.A5.EF.BC.9A.E7.94.A8.E6.88.B7.E5.90.8C.E6.84.8F.E6.8E.88.E6.9D.83.EF.BC.8C.E8.8E.B7.E5.8F.96code">开发文档</a></p>
     *
     * @param redirectUri 重定向地址
     * @param scope       授权作用域
     * @param state       原样返回的数据
     * @return
     */
    public static String buildRedirectUri(String redirectUri, OAuthScope scope, String state) {
        RedirectUriRequest request = new RedirectUriRequest();
        request.setRedirectUri(redirectUri);
        request.setScope(scope);
        request.setState(state);
        return buildRedirectUri(request);
    }

    /**
     * 生成OAuth重定向URI（用户同意授权，获取code）
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E4.B8.80.E6.AD.A5.EF.BC.9A.E7.94.A8.E6.88.B7.E5.90.8C.E6.84.8F.E6.8E.88.E6.9D.83.EF.BC.8C.E8.8E.B7.E5.8F.96code">开发文档</a></p>
     *
     * @param redirectUri 重定向地址
     * @param scope       授权作用域
     * @return
     */
    public static String buildRedirectUri(String redirectUri, OAuthScope scope) {
        return buildRedirectUri(redirectUri, scope, "wechatboot");
    }

    /**
     * 通过code换取网页授权access_token
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E4.BA.8C.E6.AD.A5.EF.BC.9A.E9.80.9A.E8.BF.87code.E6.8D.A2.E5.8F.96.E7.BD.91.E9.A1.B5.E6.8E.88.E6.9D.83access_token">开发文档</a></p>
     *
     * @param request
     * @return
     */
    public static GetAccessTokenResponse getAccessToken(GetAccessTokenRequest request) throws WechatException {
        String response = Http.get(HTTPS_API_WEIXIN_QQ_COM_SNS_OAUTH2_ACCESS_TOKEN, request);
        Checker.checkApiResponse(response);
        return Converter.fromJSON(response, GetAccessTokenResponse.class);
    }

    /**
     * 通过code换取网页授权access_token
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E4.BA.8C.E6.AD.A5.EF.BC.9A.E9.80.9A.E8.BF.87code.E6.8D.A2.E5.8F.96.E7.BD.91.E9.A1.B5.E6.8E.88.E6.9D.83access_token">开发文档</a></p>
     *
     * @param code 授权回调的code
     * @return
     */
    public static GetAccessTokenResponse getAccessToken(String code) throws WechatException {
        GetAccessTokenRequest request = new GetAccessTokenRequest();
        request.setCode(code);
        return getAccessToken(request);
    }

    /**
     * 刷新access_token（如果需要）
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E4.B8.89.E6.AD.A5.EF.BC.9A.E5.88.B7.E6.96.B0access_token.EF.BC.88.E5.A6.82.E6.9E.9C.E9.9C.80.E8.A6.81.EF.BC.89">开发文档</a></p>
     *
     * @param request
     * @return
     */
    public static RefreshAccessTokenResponse refreshAccessToken(RefreshAccessTokenRequest request) throws WechatException {
        String response = Http.get(HTTPS_API_WEIXIN_QQ_COM_SNS_OAUTH2_REFRESH_TOKEN, request);
        Checker.checkApiResponse(response);
        return Converter.fromJSON(response, RefreshAccessTokenResponse.class);
    }

    /**
     * 刷新access_token（如果需要）
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E4.B8.89.E6.AD.A5.EF.BC.9A.E5.88.B7.E6.96.B0access_token.EF.BC.88.E5.A6.82.E6.9E.9C.E9.9C.80.E8.A6.81.EF.BC.89">开发文档</a></p>
     *
     * @param refreshToken
     * @return
     */
    public static RefreshAccessTokenResponse refreshAccessToken(String refreshToken) throws WechatException {
        RefreshAccessTokenRequest request = new RefreshAccessTokenRequest();
        request.setRefreshToken(refreshToken);
        return refreshAccessToken(request);
    }

    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E5.9B.9B.E6.AD.A5.EF.BC.9A.E6.8B.89.E5.8F.96.E7.94.A8.E6.88.B7.E4.BF.A1.E6.81.AF.28.E9.9C.80scope.E4.B8.BA_snsapi_userinfo.29">开发文档</a></p>
     *
     * @param request
     * @return
     */
    public static GetUserinfoResponse getUserinfo(GetUserinfoRequest request) throws WechatException {
        String response = Http.get(HTTPS_API_WEIXIN_QQ_COM_SNS_USERINFO, request);
        Checker.checkApiResponse(response);
        return Converter.fromJSON(response, GetUserinfoResponse.class);
    }

    /**
     * 拉取用户信息(需scope为 snsapi_userinfo)
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E7.AC.AC.E5.9B.9B.E6.AD.A5.EF.BC.9A.E6.8B.89.E5.8F.96.E7.94.A8.E6.88.B7.E4.BF.A1.E6.81.AF.28.E9.9C.80scope.E4.B8.BA_snsapi_userinfo.29">开发文档</a></p>
     *
     * @param accessToken 网页授权accessToken
     * @param openid 用户标志
     * @return
     */
    public static GetUserinfoResponse getUserinfo(String accessToken, String openid) throws WechatException {
        GetUserinfoRequest request = new GetUserinfoRequest();
        request.setAccessToken(accessToken);
        request.setOpenid(openid);
        return getUserinfo(request);
    }


    /**
     * 检验授权凭证（access_token）是否有效
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E9.99.84.EF.BC.9A.E6.A3.80.E9.AA.8C.E6.8E.88.E6.9D.83.E5.87.AD.E8.AF.81.EF.BC.88access_token.EF.BC.89.E6.98.AF.E5.90.A6.E6.9C.89.E6.95.88">开发文档</a></p>
     *
     * @param request
     * @return
     */
    public static ValidAccessTokenResponse validAccessToken(ValidAccessTokenRequest request) throws WechatException {
        String response = Http.get(HTTPS_API_WEIXIN_QQ_COM_SNS_AUTH, request);
        Checker.checkApiResponse(response);
        return Converter.fromJSON(response, ValidAccessTokenResponse.class);
    }

    /**
     * 检验授权凭证（access_token）是否有效
     * <p>参考<a href="http://mp.weixin.qq.com/wiki/17/c0f37d5704f0b64713d5d2c37b468d75.html#.E9.99.84.EF.BC.9A.E6.A3.80.E9.AA.8C.E6.8E.88.E6.9D.83.E5.87.AD.E8.AF.81.EF.BC.88access_token.EF.BC.89.E6.98.AF.E5.90.A6.E6.9C.89.E6.95.88">开发文档</a></p>
     *
     * @param accessToken 网页授权accessToken
     * @param openid 用户标志
     * @return
     */
    public static ValidAccessTokenResponse validAccessToken(String accessToken, String openid) throws WechatException {
        ValidAccessTokenRequest request = new ValidAccessTokenRequest();
        request.setAccessToken(accessToken);
        request.setOpenid(openid);
        return validAccessToken(request);
    }

}
