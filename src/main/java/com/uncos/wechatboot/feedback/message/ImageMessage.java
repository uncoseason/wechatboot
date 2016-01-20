package com.uncos.wechatboot.feedback.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 图片消息
 * Created by xuwen on 2016/1/16.
 */
public class ImageMessage extends MediaMessage {

    private String picUrl; // 图片链接

    @JacksonXmlProperty(localName = "PicUrl")
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
