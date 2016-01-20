package com.uncos.wechatboot.api.message.protocol;

import com.uncos.wechatboot.common.Message;
import com.uncos.wechatboot.common.MsgType;
import com.uncos.wechatboot.common.Video;

/**
 * Created by xuwen on 2016/1/17.
 */
public class VideoMessage extends Message {

    private Video video;

    public VideoMessage() {
        this.msgType = MsgType.video;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
