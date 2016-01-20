package com.uncos.wechatboot.api.message.protocol;

import com.uncos.wechatboot.common.Image;
import com.uncos.wechatboot.common.Message;
import com.uncos.wechatboot.common.MsgType;

/**
 * Created by xuwen on 2016/1/17.
 */
public class ImageMessage extends Message {

    private Image image;

    public ImageMessage() {
        this.msgType = MsgType.image;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }
}
