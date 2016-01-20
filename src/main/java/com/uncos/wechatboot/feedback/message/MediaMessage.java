package com.uncos.wechatboot.feedback.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 包含视频消息媒体id的消息
 * Created by xuwen on 2016/1/16.
 */
public class MediaMessage extends IdMessage {

    protected String mediaId; // 视频消息媒体id，可以调用多媒体文件下载接口拉取数据

    @JacksonXmlProperty(localName = "MediaId")
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
