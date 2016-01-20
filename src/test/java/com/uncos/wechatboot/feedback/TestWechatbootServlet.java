package com.uncos.wechatboot.feedback;

import com.uncos.wechatboot.common.Article;
import com.uncos.wechatboot.feedback.message.TextMessage;
import com.uncos.wechatboot.feedback.response.Response;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuwen on 2016/1/17.
 */
public class TestWechatbootServlet extends WechatbootServlet {

    /**
     * 文本消息
     *
     * @param textMessage
     * @return
     */
    @Override
    protected Response onTextMessage(TextMessage textMessage) {
        switch (textMessage.getContent()) {
            case "1":
                return createTextResponse("回复一个文本消息");
            case "2":
                return createImageResponse("eqUhUq0pxtaWTj9CGr4ajBnn5Gsu3LpjhnRrGK6qZnAz4xRoCu80jjHFoWYuKINv");
            case "3":
                return createVoiceResponse("eqUhUq0pxtaWTj9CGr4ajBnn5Gsu3LpjhnRrGK6qZnAz4xRoCu80jjHFoWYuKINv");
            case "4":
                return createVideoResponse("FEhrWtq3bESfA9XP2hjoVeUDX8_r3GwkzE1pwbvPn-VzZqNfICbUkCqJuSCaCfkz", "视频标题", "视频介绍");
            case "5":
                return createMusicResponse("音乐标题", "音乐介绍", "http://mp.weixin.qq.com/wiki/home/index.html", "http://mp.weixin.qq.com/wiki/home/index.html", "eqUhUq0pxtaWTj9CGr4ajBnn5Gsu3LpjhnRrGK6qZnAz4xRoCu80jjHFoWYuKINv");
            case "6":
                return createNewsResponse("图文标题","图文介绍","http://mp.weixin.qq.com/debug/zh_CN/htmledition/images/bg/bg_logo1f2fc8.png","http://mp.weixin.qq.com/wiki/home/index.html");
            case "7":
                List<Article> articles = new ArrayList<>();
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
                return createNewsResponse(articles);
            default:
                return null;
        }
    }
}
