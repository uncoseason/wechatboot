package com.uncos.wechatboot.common;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 图文
 * Created by xuwen on 2016/1/17.
 */
public class Article extends Description {

    private String picUrl; // 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
    private String url; // 点击图文消息跳转链接

    @JacksonXmlProperty(localName = "PicUrl")
    @JacksonXmlCData
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @JacksonXmlProperty(localName = "Url")
    @JacksonXmlCData
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
