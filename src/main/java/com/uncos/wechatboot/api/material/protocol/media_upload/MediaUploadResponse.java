package com.uncos.wechatboot.api.material.protocol.media_upload;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.common.MaterialType;

/**
 * Created by xuwen on 2016/1/17.
 */
public class MediaUploadResponse {

    private MaterialType type;
    private String mediaId;
    private String thumbMediaId;
    private long createAt;

    public MaterialType getType() {
        return type;
    }

    public void setType(MaterialType type) {
        this.type = type;
    }

    @JsonProperty("media_id")
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @JsonProperty("thumb_media_id")
    public String getThumbMediaId() {
        return thumbMediaId;
    }

    public void setThumbMediaId(String thumbMediaId) {
        this.thumbMediaId = thumbMediaId;
    }

    @JsonProperty("created_at")
    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }
}
