package com.uncos.wechatboot.api.message.protocol;

import com.uncos.wechatboot.common.Message;
import com.uncos.wechatboot.common.MsgType;
import com.uncos.wechatboot.common.Music;

/**
 * Created by xuwen on 2016/1/17.
 */
public class MusicMessage extends Message {

    private Music music;

    public MusicMessage() {
        this.msgType = MsgType.music;
    }

    public Music getMusic() {
        return music;
    }

    public void setMusic(Music music) {
        this.music = music;
    }
}
