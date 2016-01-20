package com.uncos.wechatboot.api.message.protocol;

import com.uncos.wechatboot.common.Message;
import com.uncos.wechatboot.common.MsgType;
import com.uncos.wechatboot.common.News;

/**
 * Created by xuwen on 2016/1/17.
 */
public class NewsMessage extends Message {

    private News news;

    public NewsMessage() {
        this.msgType = MsgType.news;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }
}
