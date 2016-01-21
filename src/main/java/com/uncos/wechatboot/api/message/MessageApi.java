package com.uncos.wechatboot.api.message;

import com.uncos.wechatboot.api.message.protocol.*;
import com.uncos.wechatboot.common.*;
import com.uncos.wechatboot.exception.WechatException;
import com.uncos.wechatboot.utils.Checker;
import com.uncos.wechatboot.utils.CredentialCenter;
import com.uncos.wechatboot.utils.Http;

import java.util.ArrayList;
import java.util.List;

/**
 * 客户接口主动发送消息API
 * Created by xuwen on 2016/1/17.
 */
public class MessageApi {

    /*客服接口-发消息*/
    private static final String API_CUSTOM_SEND = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

    private static MessageApi instance = new MessageApi();

    public static MessageApi instance() {
        return instance;
    }

    /**
     * 发送文本消息
     *
     * @param message
     * @throws WechatException
     */
    public void sendTextMessage(TextMessage message) throws WechatException {
        send(message);
    }

    /**
     * 发送文本消息
     *
     * @param touser  用户标识
     * @param content 文本内容
     * @throws WechatException
     */
    public void sendTextMessage(String touser, String content) throws WechatException {
        TextMessage message = new TextMessage();
        message.setTouser(touser);
        Text text = new Text();
        text.setContent(content);
        message.setText(text);
        sendTextMessage(message);
    }

    /**
     * 发送图片消息
     *
     * @param message
     * @throws WechatException
     */
    public void sendImageMessage(ImageMessage message) throws WechatException {
        send(message);
    }

    /**
     * 发送图片消息
     *
     * @param touser  用户标识
     * @param mediaId 发送的图片/语音/视频的媒体ID
     * @throws WechatException
     */
    public void sendImageMessage(String touser, String mediaId) throws WechatException {
        ImageMessage message = new ImageMessage();
        message.setTouser(touser);
        Image image = new Image();
        image.setMediaId(mediaId);
        message.setImage(image);
        sendImageMessage(message);
    }

    /**
     * 发送语音消息
     *
     * @param message
     * @throws WechatException
     */
    public void sendVoiceMessage(VoiceMessage message) throws WechatException {
        send(message);
    }

    /**
     * 发送语音消息
     *
     * @param touser  用户标识
     * @param mediaId 发送的图片/语音/视频的媒体ID
     * @throws WechatException
     */
    public void sendVoiceMessage(String touser, String mediaId) throws WechatException {
        VoiceMessage message = new VoiceMessage();
        message.setTouser(touser);
        Voice voice = new Voice();
        voice.setMediaId(mediaId);
        message.setVoice(voice);
        sendVoiceMessage(message);
    }

    /**
     * 发送视频消息
     *
     * @param message
     * @throws WechatException
     */
    public void sendVideoMessage(VideoMessage message) throws WechatException {
        send(message);
    }

    /**
     * 发送视频消息
     *
     * @param touser       用户标识
     * @param mediaId      发送的图片/语音/视频的媒体ID
     * @param thumbMediaId 缩略图的媒体ID
     * @param title        图文消息/视频消息/音乐消息的标题
     * @param description  图文消息/视频消息/音乐消息的描述
     * @throws WechatException
     */
    public void sendVideoMessage(String touser, String mediaId, String thumbMediaId, String title, String description) throws WechatException {
        VideoMessage message = new VideoMessage();
        message.setTouser(touser);
        Video video = new Video();
        video.setMediaId(mediaId);
        video.setThumbMediaId(thumbMediaId);
        video.setTitle(title);
        video.setDescription(description);
        message.setVideo(video);
        sendVideoMessage(message);
    }

    /**
     * 发送音乐消息
     *
     * @param message
     * @throws WechatException
     */
    public void sendMusicMessage(MusicMessage message) throws WechatException {
        send(message);
    }

    /**
     * 发送音乐消息
     *
     * @param touser       用户标识
     * @param title        图文消息/视频消息/音乐消息的标题
     * @param description  图文消息/视频消息/音乐消息的描述
     * @param musicUrl     音乐链接
     * @param hqMusicUrl   高品质音乐链接，wifi环境优先使用该链接播放音乐
     * @param thumbMediaId 缩略图的媒体ID
     * @throws WechatException
     */
    public void sendMusicMessage(String touser, String title, String description, String musicUrl, String hqMusicUrl, String thumbMediaId) throws WechatException {
        MusicMessage message = new MusicMessage();
        message.setTouser(touser);
        Music music = new Music();
        music.setTitle(title);
        music.setDescription(description);
        music.setMusicUrl(musicUrl);
        music.setHqMusicUrl(hqMusicUrl);
        music.setThumbMediaId(thumbMediaId);
        message.setMusic(music);
        sendMusicMessage(message);
    }

    /**
     * 发送图文消息
     *
     * @param message
     * @throws WechatException
     */
    public void sendNewsMessage(NewsMessage message) throws WechatException {
        send(message);
    }

    /**
     * 发送图文消息
     *
     * @param touser   用户标识
     * @param articles 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
     * @throws WechatException
     */
    public void sendNewsMessage(String touser, List<Article> articles) throws WechatException {
        NewsMessage message = new NewsMessage();
        News news = new News();
        news.setArticles(articles);
        message.setTouser(touser);
        message.setNews(news);
        sendNewsMessage(message);
    }

    /**
     * 发送图文消息
     *
     * @param touser      用户标识
     * @param title       图文消息/视频消息/音乐消息的标题
     * @param description 图文消息/视频消息/音乐消息的描述
     * @param picUrl      图文消息的图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80
     * @param url         图文消息被点击后跳转的链接
     * @throws WechatException
     */
    public void sendNewsMessage(String touser, String title, String description, String picUrl, String url) throws WechatException {
        List<Article> news = new ArrayList<Article>();
        Article article = new Article();
        article.setTitle(title);
        article.setDescription(description);
        article.setPicUrl(picUrl);
        article.setUrl(url);
        news.add(article);
        sendNewsMessage(touser, news);
    }

    /**
     * 发送消息
     *
     * @param message
     * @throws WechatException
     */
    private void send(Message message) throws WechatException {
        String result = Http.post(CredentialCenter.writeAccessTokenURL(API_CUSTOM_SEND), message, RequestType.JSON);
        Checker.checkApiResponse(result);
    }
}
