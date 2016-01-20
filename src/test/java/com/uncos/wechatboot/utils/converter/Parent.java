package com.uncos.wechatboot.utils.converter;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

/**
 * Created by xuwen on 2016-1-6.
 */
public class Parent {

    public Parent() {
    }

    @JsonProperty("p1")
    @JacksonXmlProperty(localName = "p1")
    @JacksonXmlCData
    private String parentField1;
    private int parentField2;

    public Parent(String parentField1, int parentField2) {
        this.parentField1 = parentField1;
        this.parentField2 = parentField2;
    }

    public String getParentField1() {
        return parentField1;
    }

    public void setParentField1(String parentField1) {
        this.parentField1 = parentField1;
    }

    public int getParentField2() {
        return parentField2;
    }

    public void setParentField2(int parentField2) {
        this.parentField2 = parentField2;
    }

    @Override
    public String toString() {
        return "Parent{" +
                "parentField1='" + parentField1 + '\'' +
                ", parentField2=" + parentField2 +
                '}';
    }
}
