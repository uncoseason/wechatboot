package com.uncos.wechatboot.common;

/**
 * 消息类型
 * Created by xuwen on 2016/1/16.
 */
public enum MsgType {

    // 通用
    
    /**
     * 文本消息
     */
    text,
    /**
     * 图片消息
     */
    image,
    /**
     * 语音消息
     */
    voice,
    /**
     * 视频消息
     */
    video,
    
    // 仅用于接收
    
    /**
     * 小视频消息
     */
    shortvideo,
    /**
     * 地理位置消息
     */
    location,
    /**
     * 链接消息
     */
    link,
    /**
     * 事件消息
     */
    event,
    
    // 仅用于回复
    
    /**
     * 音乐消息
     */
    music,
    /**
     * 图文消息
     */
    news,
}
