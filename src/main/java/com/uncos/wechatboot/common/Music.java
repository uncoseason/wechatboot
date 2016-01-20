package com.uncos.wechatboot.common;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 音乐
 * Created by xuwen on 2016/1/16.
 */
public class Music extends Description{

    private String musicUrl;
    private String hqMusicUrl;
    private String thumbMediaId;

    @JacksonXmlProperty(localName = "MusicUrl")
    @JacksonXmlCData
    @JsonProperty("musicurl")
    public String getMusicUrl() {
        return musicUrl;
    }

    public void setMusicUrl(String musicUrl) {
        this.musicUrl = musicUrl;
    }

    @JacksonXmlProperty(localName = "HQMusicUrl")
    @JacksonXmlCData
    @JsonProperty("hqmusicurl")
    public String getHqMusicUrl() {
        return hqMusicUrl;
    }

    public void setHqMusicUrl(String hqMusicUrl) {
        this.hqMusicUrl = hqMusicUrl;
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
