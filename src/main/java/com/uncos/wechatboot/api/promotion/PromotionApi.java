package com.uncos.wechatboot.api.promotion;

import com.uncos.wechatboot.api.promotion.protocol.qrcode_create.QrcodeCreateRequest;
import com.uncos.wechatboot.api.promotion.protocol.qrcode_create.QrcodeCreateResponse;
import com.uncos.wechatboot.api.promotion.protocol.shorturl.ShorturlRequest;
import com.uncos.wechatboot.api.promotion.protocol.shorturl.ShorturlResponse;
import com.uncos.wechatboot.api.promotion.protocol.showqrcode.ShowqrcodeRequest;
import com.uncos.wechatboot.api.promotion.protocol.showqrcode.ShowqrcodeResponse;
import com.uncos.wechatboot.common.QrcodeType;
import com.uncos.wechatboot.common.RequestType;
import com.uncos.wechatboot.exception.WechatException;
import com.uncos.wechatboot.utils.*;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 推广支持API
 * Created by xuwen on 2016/1/17.
 */
public class PromotionApi {

    /*创建二维码ticket*/
    private static final String API_QRCODE_CREATE = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token=TOKEN";
    /*通过ticket换取二维码*/
    private static final String API_SHOWQRCODE = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=TICKET";
    /*长链接转短链接接口*/
    private static final String API_SHORTURL = "https://api.weixin.qq.com/cgi-bin/shorturl?access_token=ACCESS_TOKEN";

    private static PromotionApi instance = new PromotionApi();

    public static PromotionApi instance() {
        return instance;
    }

    /**
     * 创建二维码
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public QrcodeCreateResponse qrcodeCreate(QrcodeCreateRequest request) throws WechatException {
        String result = Http.post(CredentialCenter.writeAccessTokenURL(API_QRCODE_CREATE), request, RequestType.JSON);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, QrcodeCreateResponse.class);
    }

    /**
     * 创建临时二维码
     *
     * @param sceneId       场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @param expireSeconds 该二维码有效时间，以秒为单位。 最大不超过2592000（即30天），此字段如果不填，则默认有效期为30秒
     * @return
     * @throws WechatException
     */
    public QrcodeCreateResponse qrcodeCreateTemporary(long sceneId, int expireSeconds) throws WechatException {
        QrcodeCreateRequest request = new QrcodeCreateRequest();
        request.setExpireSeconds(expireSeconds);
        request.setActionName(QrcodeType.QR_SCENE);
        QrcodeCreateRequest.Scene scene = request.new Scene();
        scene.setSceneId(sceneId);
        request.setActionInfo(scene);
        return qrcodeCreate(request);
    }

    /**
     * 创建永久二维码
     *
     * @param sceneId 场景值ID，临时二维码时为32位非0整型，永久二维码时最大值为100000（目前参数只支持1--100000）
     * @return
     * @throws WechatException
     */
    public QrcodeCreateResponse qrcodeCreatePerpetual(long sceneId) throws WechatException {
        QrcodeCreateRequest request = new QrcodeCreateRequest();
        request.setActionName(QrcodeType.QR_LIMIT_SCENE);
        QrcodeCreateRequest.Scene scene = request.new Scene();
        scene.setSceneId(sceneId);
        request.setActionInfo(scene);
        return qrcodeCreate(request);
    }

    /**
     * 创建永久二维码
     *
     * @param sceneStr 场景值ID（字符串形式的ID），字符串类型，长度限制为1到64，仅永久二维码支持此字段
     * @return
     * @throws WechatException
     */
    public QrcodeCreateResponse qrcodeCreatePerpetual(String sceneStr) throws WechatException {
        QrcodeCreateRequest request = new QrcodeCreateRequest();
        request.setActionName(QrcodeType.QR_LIMIT_SCENE);
        QrcodeCreateRequest.Scene scene = request.new Scene();
        scene.setSceneStr(sceneStr);
        request.setActionInfo(scene);
        return qrcodeCreate(request);
    }

    /**
     * 下载二维码图片
     *
     * @param request
     * @return 二维码图片文件
     * @throws IOException
     */
    public ShowqrcodeResponse showqrcode(ShowqrcodeRequest request) throws IOException {
        File file = File.createTempFile(RandomStringGenerator.generate(), ".jpg");
        InputStream is = Http.getFile(API_SHOWQRCODE, request);
        FileUtils.copyInputStreamToFile(is, file);
        ShowqrcodeResponse response = new ShowqrcodeResponse();
        response.setFile(file);
        return response;
    }

    /**
     * 下载二维码图片
     *
     * @param ticket 获取的二维码ticket，凭借此ticket可以在有效时间内换取二维码
     * @return 二维码图片文件
     * @throws IOException
     */
    public ShowqrcodeResponse showqrcode(String ticket) throws IOException {
        ShowqrcodeRequest request = new ShowqrcodeRequest();
        request.setTicket(ticket);
        return showqrcode(request);
    }

    /**
     * 长链接转短链接接口
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public ShorturlResponse shorturl(ShorturlRequest request) throws WechatException {
        String result = Http.post(CredentialCenter.writeAccessTokenURL(API_SHORTURL), request, RequestType.JSON);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, ShorturlResponse.class);
    }

    /**
     * 长链接转短链接接口
     *
     * @param longUrl 长链接
     * @return
     * @throws WechatException
     */
    public ShorturlResponse shorturl(String longUrl) throws WechatException {
        ShorturlRequest request = new ShorturlRequest();
        request.setLongUrl(longUrl);
        return shorturl(request);
    }
}
