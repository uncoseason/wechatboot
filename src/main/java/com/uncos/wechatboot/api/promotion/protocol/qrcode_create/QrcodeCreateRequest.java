package com.uncos.wechatboot.api.promotion.protocol.qrcode_create;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.base.request.BaseAccessTokenRequest;
import com.uncos.wechatboot.common.QrcodeType;

/**
 * Created by xuwen on 2016/1/17.
 */
public class QrcodeCreateRequest extends BaseAccessTokenRequest {

    private int expireSeconds;
    private QrcodeType actionName;
    private Scene actionInfo;

    @JsonProperty("expire_seconds")
    public int getExpireSeconds() {
        return expireSeconds;
    }

    public void setExpireSeconds(int expireSeconds) {
        this.expireSeconds = expireSeconds;
    }

    @JsonProperty("action_name")
    public QrcodeType getActionName() {
        return actionName;
    }

    public void setActionName(QrcodeType actionName) {
        this.actionName = actionName;
    }

    @JsonProperty("action_info")
    public Scene getActionInfo() {
        return actionInfo;
    }

    public void setActionInfo(Scene actionInfo) {
        this.actionInfo = actionInfo;
    }

    public class Scene {
        private long sceneId;
        private String sceneStr;

        @JsonProperty("scene_id")
        public long getSceneId() {
            return sceneId;
        }

        public void setSceneId(long sceneId) {
            this.sceneId = sceneId;
        }

        @JsonProperty("scene_str")
        public String getSceneStr() {
            return sceneStr;
        }

        public void setSceneStr(String sceneStr) {
            this.sceneStr = sceneStr;
        }
    }
}
