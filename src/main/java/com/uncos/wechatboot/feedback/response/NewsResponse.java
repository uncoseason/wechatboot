package com.uncos.wechatboot.feedback.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.common.Article;
import com.uncos.wechatboot.common.MsgType;

import java.util.List;

/**
 * 回复图文消息
 * Created by xuwen on 2016/1/17.
 */
public class NewsResponse extends Response {

    private List<Article> articles; // 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应

    public NewsResponse() {
        this.msgType = MsgType.news;
    }

    @JacksonXmlElementWrapper(localName = "Articles")
    @JacksonXmlProperty(localName = "item")
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    @JacksonXmlProperty(localName = "ArticleCount")
    public int getArticleCount() {
        return this.articles != null ? this.articles.size() : 0;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.news;
    }
}
