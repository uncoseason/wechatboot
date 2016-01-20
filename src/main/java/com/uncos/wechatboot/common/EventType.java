package com.uncos.wechatboot.common;

/**
 * 事件类型
 * Created by xuwen on 2016/1/16.
 */
public enum EventType {
    /**
     * 关注（扫描带参数二维码事件会携带EventKey）
     */
    subscribe,
    /**
     * 取消关注
     */
    unsubscribe,
    /**
     * 扫描带参数二维码事件（用户已关注）
     */
    SCAN,
    /**
     * 上报地理位置事件
     */
    LOCATION,
    /**
     * 点击菜单拉取消息时的事件推送
     */
    CLICK,
    /**
     * 点击菜单跳转链接时的事件推送
     */
    VIEW,
}
