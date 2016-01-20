package com.uncos.wechatboot.feedback.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 链接消息
 * Created by xuwen on 2016/1/16.
 */
public class LinkMessage extends IdMessage {

    private String title; // 消息标题
    private String description; // 消息描述
    private String url; // 消息链接

    @JacksonXmlProperty(localName = "Title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JacksonXmlProperty(localName = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @JacksonXmlProperty(localName = "Url")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
