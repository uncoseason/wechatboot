package com.uncos.wechatboot.api.material.protocol.media_upload;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.uncos.wechatboot.base.request.BaseAccessTokenRequest;
import com.uncos.wechatboot.common.MaterialType;

import java.io.File;

/**
 * Created by xuwen on 2016/1/17.
 */
public class MediaUploadRequest extends BaseAccessTokenRequest {

    private MaterialType type;
    private File file;

    public MaterialType getType() {
        return type;
    }

    public void setType(MaterialType type) {
        this.type = type;
    }

    @JsonIgnore
    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }
}
