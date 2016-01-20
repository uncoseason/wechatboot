package com.uncos.wechatboot.feedback.event;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.uncos.wechatboot.common.Event;

/**
 * 上报地理位置事件
 * Created by xuwen on 2016/1/16.
 */
public class LocationEvent extends Event {

    private Double latitude; // 地理位置纬度
    private Double longitude; // 地理位置经度
    private Double precision; // 地理位置精度

    @JacksonXmlProperty(localName = "Latitude")
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JacksonXmlProperty(localName = "Longitude")
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JacksonXmlProperty(localName = "Precision")
    public Double getPrecision() {
        return precision;
    }

    public void setPrecision(Double precision) {
        this.precision = precision;
    }
}
