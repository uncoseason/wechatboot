package com.uncos.wechatboot.feedback.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * 地理位置消息
 * Created by xuwen on 2016/1/16.
 */
public class LocationMessage extends IdMessage {

    private String locationX; // 地理位置纬度
    private String locationY; // 地理位置经度
    private int scale; // 地图缩放大小
    private String label; // 地理位置信息

    @JacksonXmlProperty(localName = "Location_X")
    public String getLocationX() {
        return locationX;
    }

    public void setLocationX(String locationX) {
        this.locationX = locationX;
    }

    @JacksonXmlProperty(localName = "Location_Y")
    public String getLocationY() {
        return locationY;
    }

    public void setLocationY(String locationY) {
        this.locationY = locationY;
    }

    @JacksonXmlProperty(localName = "Scale")
    public int getScale() {
        return scale;
    }

    public void setScale(int scale) {
        this.scale = scale;
    }

    @JacksonXmlProperty(localName = "Label")
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
