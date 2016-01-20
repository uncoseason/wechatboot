package com.uncos.wechatboot.feedback.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.common.MsgType;
import com.uncos.wechatboot.common.Music;

/**
 * 回复音乐消息
 * Created by xuwen on 2016/1/17.
 */
public class MusicResponse extends Response {

    private Music music;

    public MusicResponse() {
        this.msgType = MsgType.music;
    }

    @JacksonXmlProperty(localName = "Music")
    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.music;
    }
}
