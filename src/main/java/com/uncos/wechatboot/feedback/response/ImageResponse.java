package com.uncos.wechatboot.feedback.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.common.Image;
import com.uncos.wechatboot.common.MsgType;

/**
 * 回复图片消息
 * Created by xuwen on 2016/1/17.
 */
public class ImageResponse extends Response {

    private Image image;

    public ImageResponse() {
        this.msgType = MsgType.image;
    }

    @JacksonXmlProperty(localName = "Image")
    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    @Override
    public MsgType getMsgType() {
        return MsgType.image;
    }
}
