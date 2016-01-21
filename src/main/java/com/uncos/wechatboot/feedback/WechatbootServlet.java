package com.uncos.wechatboot.feedback;

import com.uncos.wechatboot.api.Wechatboot;
import com.uncos.wechatboot.common.*;
import com.uncos.wechatboot.exception.SignatureException;
import com.uncos.wechatboot.feedback.event.*;
import com.uncos.wechatboot.feedback.message.*;
import com.uncos.wechatboot.feedback.message.Message;
import com.uncos.wechatboot.feedback.response.*;
import com.uncos.wechatboot.utils.Converter;
import com.uncos.wechatboot.utils.MessageCrypt;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * wechatboot servlet
 * 开发者可以继承此类覆写自己感兴趣的消息处理方法
 * Created by xuwen on 2016/1/16.
 */
public class WechatbootServlet extends HttpServlet {

    private static Logger logger = LoggerFactory.getLogger(WechatbootServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        IOUtils.write(feedback(req), resp.getOutputStream(), Wechatboot.charset());
    }

    /**
     * 响应微信公众号消息
     *
     * @param httpServletRequest
     * @return
     */
    public String feedback(HttpServletRequest httpServletRequest) {
        // 用于签名的参数
        String signature = httpServletRequest.getParameter("signature");
        String timestamp = httpServletRequest.getParameter("timestamp");
        String nonce = httpServletRequest.getParameter("nonce");
        String echostr = httpServletRequest.getParameter("echostr");
        String encryptType = httpServletRequest.getParameter("encrypt_type");
        String msgSignature = httpServletRequest.getParameter("msg_signature");
        // 校验签名
        checkSignature(signature, Wechatboot.config().getToken(), timestamp, nonce);
        logger.info("成功校验通讯签名");
        // 校验微信接口配置
        if (StringUtils.isNotBlank(echostr)) {
            logger.info("响应微信服务器发送的接口配置验证请求，echostr={}", echostr);
            return echostr;
        }
        // 处理微信用户发送给公众号的数据
        String requestData = null;
        MessageCrypt messageCrypt = new MessageCrypt();
        try {
            requestData = IOUtils.toString(httpServletRequest.getInputStream(), Wechatboot.charset());
        } catch (IOException e) {
            logger.error("解析微信服务器发送的数据出错", e);
        }
        logger.info("收到微信服务器发送请求，signature={}，timestamp={}，nonce={}，echostr={}，encrypt_type={}，msg_signature={}\n{}", signature, timestamp, nonce, echostr, encryptType, msgSignature, requestData);
        // 消息解密
        if ("aes".equals(encryptType)) {
            EncryptMessage encryptMessage = Converter.fromXML(requestData, EncryptMessage.class);
            checkSignature(msgSignature, Wechatboot.config().getToken(), nonce, timestamp, encryptMessage.getEncrypt());
            logger.info("成功校验加密消息的签名");
            requestData = messageCrypt.decodeAES(encryptMessage.getEncrypt());
            logger.info("成功解密微信服务器发送的数据\n{}", requestData);
        }
        // 消息处理
        Response response = onMessage(requestData);
        if (response == null) {
            logger.info("暂不处理请求，根据通讯规则为请求数据响应了一个空字符串\n{}", requestData);
            return "";
        } else {
            // 准备响应
            Message message = Converter.fromXML(requestData, Message.class);
            response.setToUserName(message.getFromUserName());
            response.setFromUserName(message.getToUserName());
            response.setCreateTime(String.valueOf(new Date().getTime() / 1000L));
            String responseData = Converter.toXML(response);
            logger.info("为请求数据响应了一个消息\n请求报文\n{}\n响应报文\n{}", requestData, responseData);
            // 消息加密
            if ("aes".equals(encryptType)) {
                EncryptResponse encryptResponse = new EncryptResponse();
                encryptResponse.setNonce(nonce);
                encryptResponse.setTimeStamp(timestamp);
                encryptResponse.setEncrypt(messageCrypt.encodeAES(responseData));
                encryptResponse.setMsgSignature(signature(Wechatboot.config().getToken(), timestamp, nonce, encryptResponse.getEncrypt()));
                responseData = Converter.toXML(encryptResponse);
                logger.info("响应消息已启用AES加密，加密后的响应报文\n{}", responseData);
            }
            return responseData;
        }
    }

    /**
     * 文本消息
     *
     * @param textMessage
     * @return
     */
    protected Response onTextMessage(TextMessage textMessage) {
        logger.info("接收到文本消息{}", textMessage.toString());
        return null;
    }

    /**
     * 图片消息
     *
     * @param imageMessage
     * @return
     */
    protected Response onImageMessage(ImageMessage imageMessage) {
        logger.info("接收到图片消息{}", imageMessage.toString());
        return null;
    }

    /**
     * 开启了语音识别的语音消息
     * 如果开发者开启了语音识别应当覆写该方法而不是onVoiceMessage
     *
     * @param recongnitionMessage
     * @return
     */
    protected Response onRecongnitionMessage(RecongnitionMessage recongnitionMessage) {
        logger.info("接收到语音识别的语音消息{}", recongnitionMessage.toString());
        return null;
    }

    /**
     * 语音消息
     * 如果开发者开启了语音识别应当覆写onRecongnitionMessage而不是本方法
     *
     * @param voiceMessage
     * @return
     */
    protected Response onVoiceMessage(VoiceMessage voiceMessage) {
        logger.info("接收到语音识别的语音消息{}", voiceMessage.toString());
        return null;
    }

    /**
     * 视频消息
     *
     * @param videoMessage
     * @return
     */
    protected Response onVideoMessage(VideoMessage videoMessage) {
        logger.info("接收到视频消息{}", videoMessage.toString());
        return null;
    }

    /**
     * 小视频消息
     *
     * @param shortVideoMessage
     * @return
     */
    protected Response onShortVideoMessage(ShortVideoMessage shortVideoMessage) {
        logger.info("接收到小视频消息{}", shortVideoMessage.toString());
        return null;
    }

    /**
     * 地理位置消息
     *
     * @param locationMessage
     * @return
     */
    protected Response onLocationMessage(LocationMessage locationMessage) {
        logger.info("接收到地理位置消息{}", locationMessage.toString());
        return null;
    }

    /**
     * 链接消息
     *
     * @param linkMessage
     * @return
     */
    protected Response onLinkMessage(LinkMessage linkMessage) {
        logger.info("接收到链接消息{}", linkMessage.toString());
        return null;
    }

    /**
     * 关注事件
     * 如果是通过带参数二维码扫描关注应当覆写onScanWhenUnsubscribeEvent方法而不是本方法
     *
     * @param subscribeEvent
     * @return
     */
    protected Response onSubscribe(SubscribeEvent subscribeEvent) {
        logger.info("接收到关注事件{}", subscribeEvent.toString());
        return null;
    }

    /**
     * 取消关注事件
     *
     * @param unsubscribeEvent
     * @return
     */
    protected Response onUnsubscribe(UnsubscribeEvent unsubscribeEvent) {
        logger.info("接收到取消关注事件{}", unsubscribeEvent.toString());
        return null;
    }

    /**
     * 扫描带参数二维码事件（用户已关注）
     *
     * @param scanWhenSubscribeEvent
     * @return
     */
    protected Response onScanWhenSubscribeEvent(ScanWhenSubscribeEvent scanWhenSubscribeEvent) {
        logger.info("接收到扫描带参数二维码事件（用户已关注）{}", scanWhenSubscribeEvent.toString());
        return null;
    }

    /**
     * 扫描带参数二维码事件（用户未关注）
     * 如果通过扫描带参数二维码关注应当覆写此方法而不是onSubscribe
     *
     * @param scanWhenUnsubscribeEvent
     * @return
     */
    protected Response onScanWhenUnsubscribeEvent(ScanWhenUnsubscribeEvent scanWhenUnsubscribeEvent) {
        logger.info("接收到扫描带参数二维码事件（用户未关注）{}", scanWhenUnsubscribeEvent.toString());
        return null;
    }

    /**
     * 上报地理位置事件
     *
     * @param locationEvent
     * @return
     */
    protected Response onLocationEvent(LocationEvent locationEvent) {
        logger.info("接收到上报地理位置事件{}", locationEvent.toString());
        return null;
    }

    /**
     * 点击菜单拉取消息时的事件
     *
     * @param clickEvent
     * @return
     */
    protected Response onClickEvent(ClickEvent clickEvent) {
        logger.info("接收到点击菜单拉取消息时的事件{}", clickEvent.toString());
        return null;
    }

    /**
     * 点击菜单跳转链接时的事件
     *
     * @param viewEvent
     * @return
     */
    protected Response onViewEvent(ViewEvent viewEvent) {
        logger.info("接收到点击菜单跳转链接时的事件{}", viewEvent.toString());
        return null;
    }

    /**
     * 创建文本回复
     *
     * @param content 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
     * @return
     */
    final public Response createTextResponse(String content) {
        TextResponse response = new TextResponse();
        response.setContent(content);
        return response;
    }

    /**
     * 创建图片回复
     *
     * @param mediaId 通过素材管理接口上传多媒体文件，得到的id
     * @return
     */
    final public Response createImageResponse(String mediaId) {
        ImageResponse response = new ImageResponse();
        Image image = new Image();
        image.setMediaId(mediaId);
        response.setImage(image);
        return response;
    }

    /**
     * 创建语音回复
     *
     * @param mediaId
     * @return
     */
    final public Response createVoiceResponse(String mediaId) {
        VoiceResponse response = new VoiceResponse();
        Voice voice = new Voice();
        voice.setMediaId(mediaId);
        response.setVoice(voice);
        return response;
    }

    /**
     * 创建视频回复
     *
     * @param mediaId     通过素材管理接口上传多媒体文件，得到的id
     * @param title       视频消息的标题
     * @param description 视频消息的描述
     * @return
     */
    final public Response createVideoResponse(String mediaId, String title, String description) {
        VideoResponse response = new VideoResponse();
        Video video = new Video();
        video.setMediaId(mediaId);
        video.setTitle(title);
        video.setDescription(description);
        response.setVideo(video);
        return response;
    }

    /**
     * 创建音乐回复
     *
     * @param title        音乐标题
     * @param description  音乐描述
     * @param musicUrl     音乐链接
     * @param hqMusicUrl   高质量音乐链接，WIFI环境优先使用该链接播放音乐
     * @param thumbMediaId 缩略图的媒体id，通过素材管理接口上传多媒体文件，得到的id
     * @return
     */
    final public Response createMusicResponse(String title, String description, String musicUrl, String hqMusicUrl, String thumbMediaId) {
        MusicResponse response = new MusicResponse();
        Music music = new Music();
        music.setTitle(title);
        music.setDescription(description);
        music.setMusicUrl(musicUrl);
        music.setHqMusicUrl(hqMusicUrl);
        music.setThumbMediaId(thumbMediaId);
        response.setMusic(music);
        return response;
    }

    /**
     * 创建图文回复（多条）
     *
     * @param articles 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
     * @return
     */
    final public Response createNewsResponse(List<Article> articles) {
        NewsResponse response = new NewsResponse();
        response.setArticles(articles);
        return response;
    }

    /**
     * 创建图文回复（单条）
     *
     * @param title       图文消息标题
     * @param description 图文消息描述
     * @param picUrl      图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
     * @param url         点击图文消息跳转链接
     * @return
     */
    final public Response createNewsResponse(String title, String description, String picUrl, String url) {
        Article article = new Article();
        article.setTitle(title);
        article.setDescription(description);
        article.setPicUrl(picUrl);
        article.setUrl(url);
        ArrayList<Article> articles = new ArrayList<Article>(1);
        articles.add(article);
        return createNewsResponse(articles);
    }

    /**
     * 消息处理
     *
     * @param requestData 微信发送过来的数据
     * @return
     */
    private Response onMessage(String requestData) {
        Message message = Converter.fromXML(requestData, Message.class);
        switch (message.getMsgType()) {
            case text:
                return onTextMessage(Converter.fromXML(requestData, TextMessage.class));
            case image:
                return onImageMessage(Converter.fromXML(requestData, ImageMessage.class));
            case voice:
                RecongnitionMessage recongnitionMessage = Converter.fromXML(requestData, RecongnitionMessage.class);
                if (StringUtils.isNotBlank(recongnitionMessage.getRecognition())) {
                    return onRecongnitionMessage(recongnitionMessage);
                } else {
                    return onVoiceMessage(Converter.fromXML(requestData, VoiceMessage.class));
                }
            case video:
                return onVideoMessage(Converter.fromXML(requestData, VideoMessage.class));
            case shortvideo:
                return onShortVideoMessage(Converter.fromXML(requestData, ShortVideoMessage.class));
            case location:
                return onLocationMessage(Converter.fromXML(requestData, LocationMessage.class));
            case link:
                return onLinkMessage(Converter.fromXML(requestData, LinkMessage.class));
            case event:
                return onEvent(requestData);
            default:
                logger.warn("接收到未知的消息类型[{}]", message.getMsgType());
                return null;
        }
    }

    /**
     * 事件处理
     *
     * @param requestData 微信发送过来的数据
     * @return
     */
    private Response onEvent(String requestData) {
        Event event = Converter.fromXML(requestData, Event.class);
        switch (event.getEvent()) {
            case subscribe:
                ScanWhenUnsubscribeEvent scanWhenUnsubscribeEvent = Converter.fromXML(requestData, ScanWhenUnsubscribeEvent.class);
                if (StringUtils.isNotBlank(scanWhenUnsubscribeEvent.getEventKey()) && StringUtils.isNotBlank(scanWhenUnsubscribeEvent.getTicket())) {
                    return onScanWhenUnsubscribeEvent(scanWhenUnsubscribeEvent);
                } else {
                    return onSubscribe(Converter.fromXML(requestData, SubscribeEvent.class));
                }
            case unsubscribe:
                return onUnsubscribe(Converter.fromXML(requestData, UnsubscribeEvent.class));
            case SCAN:
                return onScanWhenSubscribeEvent(Converter.fromXML(requestData, ScanWhenSubscribeEvent.class));
            case LOCATION:
                return onLocationEvent(Converter.fromXML(requestData, LocationEvent.class));
            case CLICK:
                return onClickEvent(Converter.fromXML(requestData, ClickEvent.class));
            case VIEW:
                return onViewEvent(Converter.fromXML(requestData, ViewEvent.class));
            default:
                logger.warn("接收到未知的事件类型[{}]", event.getEvent());
                return null;
        }
    }

    /**
     * 微信公众后台通讯签名
     *
     * @param signature 签名
     * @param items     参与签名的数据
     * @return
     */
    private void checkSignature(String signature, String... items) {
        if (!signature.equals(signature(items))) {
            throw new SignatureException();
        }
    }

    /**
     * SHA1签名
     *
     * @param items
     */
    private String signature(String... items) {
        Arrays.sort(items, String.CASE_INSENSITIVE_ORDER);
        StringBuilder temp = new StringBuilder();
        for (String item : items) {
            temp.append(item);
        }
        return DigestUtils.sha1Hex(temp.toString());
    }
}
