package com.uncos.wechatboot.api.material.protocol.media_get;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.base.request.BaseAccessTokenRequest;

/**
 * Created by xuwen on 2016/1/18.
 */
public class MediaGetRequest extends BaseAccessTokenRequest {

    private String mediaId;

    @JsonProperty("media_id")
    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }
}
