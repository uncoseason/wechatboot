package com.uncos.wechatboot.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 视频
 * Created by xuwen on 2016/1/16.
 */
public class Video extends Description {

    private String mediaId; // 通过素材管理接口上传多媒体文件，得到的id
    private String thumbMediaId;

    @JacksonXmlProperty(localName = "MediaId")
    @JacksonXmlCData
    @JsonProperty("media_id")
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @JacksonXmlProperty(localName = "ThumbMediaId")
    @JacksonXmlCData
    @JsonProperty("thumb_media_id")
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }
}
