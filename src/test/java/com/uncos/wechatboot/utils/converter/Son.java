package com.uncos.wechatboot.utils.converter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by xuwen on 2016-1-6.
 */
public class Son extends Parent {

    private String sonField1;
    private int sonField2;

    public Son(){}

    public Son(String sonField1, int sonField2) {
        this.sonField1 = sonField1;
        this.sonField2 = sonField2;
    }

    public Son(String parentField1, int parentField2, String sonField1, int sonField2) {
        super(parentField1, parentField2);
        this.sonField1 = sonField1;
        this.sonField2 = sonField2;
    }

    @JsonProperty("s1")
    @JacksonXmlProperty(localName = "s1")
    @JacksonXmlCData
    public String getSonField1() {
        return sonField1;
    }

    public void setSonField1(String sonField1) {
        this.sonField1 = sonField1;
    }

    public int getSonField2() {
        return sonField2;
    }

    public void setSonField2(int sonField2) {
        this.sonField2 = sonField2;
    }

    @Override
    public String toString() {
        return "Son{" +
                "sonField1='" + sonField1 + '\'' +
                ", sonField2=" + sonField2 +
                "} " + super.toString();
    }
}
