package com.uncos.wechatboot.api.material.protocol.get_material_count;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by xuwen on 2016/1/18.
 */
public class GetMaterialCountResponse {

    private long voiceCount;
    private long videoCount;
    private long imageCount;
    private long newsCount;

    @JsonProperty("voice_count")
    public long getVoiceCount() {
        return voiceCount;
    }

    public void setVoiceCount(long voiceCount) {
        this.voiceCount = voiceCount;
    }

    @JsonProperty("video_count")
    public long getVideoCount() {
        return videoCount;
    }

    public void setVideoCount(long videoCount) {
        this.videoCount = videoCount;
    }

    @JsonProperty("image_count")
    public long getImageCount() {
        return imageCount;
    }

    public void setImageCount(long imageCount) {
        this.imageCount = imageCount;
    }

    @JsonProperty("news_count")
    public long getNewsCount() {
        return newsCount;
    }

    public void setNewsCount(long newsCount) {
        this.newsCount = newsCount;
    }
}
