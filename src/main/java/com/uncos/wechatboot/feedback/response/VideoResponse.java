package com.uncos.wechatboot.feedback.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.common.MsgType;
import com.uncos.wechatboot.common.Video;

/**
 * 回复视频消息
 * Created by xuwen on 2016/1/17.
 */
public class VideoResponse extends Response {

    private Video video;

    public VideoResponse() {
        this.msgType = MsgType.video;
    }

    @JacksonXmlProperty(localName = "Video")
    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.video;
    }
}
