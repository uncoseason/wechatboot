package com.uncos.wechatboot.api;

import com.uncos.wechatboot.api.material.protocol.media_upload.MediaUploadResponse;
import com.uncos.wechatboot.common.Article;
import com.uncos.wechatboot.common.MaterialType;
import com.uncos.wechatboot.exception.WechatException;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuwen on 2016/1/17.
 */
public class MessageApiTest {

    @Test
    public void testSendTextMessage() throws WechatException {
        Wechatboot.messageApi().sendTextMessage("ouP0Yv3rPy5xPnzLD45g3psCJqZQ", "回复一个文本消息");
        System.out.println("测试 发送文本消息");
    }

    @Test
    public void testSendImageMessage() throws WechatException {
        Wechatboot.messageApi().sendImageMessage("ouP0Yv3rPy5xPnzLD45g3psCJqZQ", "eqUhUq0pxtaWTj9CGr4ajBnn5Gsu3LpjhnRrGK6qZnAz4xRoCu80jjHFoWYuKINv");
        System.out.println("测试 发送图片消息");
    }

    @Test
    public void testSendVoiceMessage() throws WechatException {
        Wechatboot.messageApi().sendVoiceMessage("ouP0Yv3rPy5xPnzLD45g3psCJqZQ", "eqUhUq0pxtaWTj9CGr4ajBnn5Gsu3LpjhnRrGK6qZnAz4xRoCu80jjHFoWYuKINv");
        System.out.println("测试 发送语音消息");
    }

    @Test
    public void testSendVideoMessage() throws Exception {
        MediaUploadResponse ImageResponse = Wechatboot.materialApi().mediaUpload(MaterialType.thumb, new File(MaterialApiTest.class.getClassLoader().getResource("test.jpg").toURI()));
        MediaUploadResponse videoResponse = Wechatboot.materialApi().mediaUpload(MaterialType.video, new File(MaterialApiTest.class.getClassLoader().getResource("test.mp4").toURI()));
        Wechatboot.messageApi().sendVideoMessage("ouP0Yv3rPy5xPnzLD45g3psCJqZQ", videoResponse.getMediaId(), ImageResponse.getMediaId(), "视频标题", "视频介绍");
        System.out.println("测试 发送视频消息");
    }

    @Test
    public void testSendMusicMessage() throws WechatException {
        Wechatboot.messageApi().sendMusicMessage("ouP0Yv3rPy5xPnzLD45g3psCJqZQ", "音乐标题", "音乐介绍", "http://mp.weixin.qq.com/wiki/home/index.html", "http://mp.weixin.qq.com/wiki/home/index.html", "eqUhUq0pxtaWTj9CGr4ajBnn5Gsu3LpjhnRrGK6qZnAz4xRoCu80jjHFoWYuKINv");
        System.out.println("测试 发送音乐消息");
    }

    @Test
    public void testSendNewsMessage() throws WechatException {
        Wechatboot.messageApi().sendNewsMessage("ouP0Yv3rPy5xPnzLD45g3psCJqZQ", "图文标题", "图文介绍", "http://mp.weixin.qq.com/debug/zh_CN/htmledition/images/bg/bg_logo1f2fc8.png", "http://mp.weixin.qq.com/wiki/home/index.html");
        System.out.println("测试 发送图文消息");

        List<Article> articles = new ArrayList<Article>();
        Article article1 = new Article();
        article1.setTitle("图文标题1");
        article1.setDescription("图文介绍1");
        article1.setPicUrl("http://mp.weixin.qq.com/debug/zh_CN/htmledition/images/bg/bg_logo1f2fc8.png");
        article1.setUrl("http://mp.weixin.qq.com/wiki/home/index.html");
        articles.add(article1);
        Article article2 = new Article();
        article2.setTitle("图文标题2");
        article2.setDescription("图文介绍2");
        article2.setPicUrl("http://mp.weixin.qq.com/debug/zh_CN/htmledition/images/bg/bg_logo1f2fc8.png");
        article2.setUrl("http://mp.weixin.qq.com/wiki/home/index.html");
        articles.add(article2);
        Wechatboot.messageApi().sendNewsMessage("ouP0Yv3rPy5xPnzLD45g3psCJqZQ", articles);
        System.out.println("测试 发送多个图文消息");
    }
}
