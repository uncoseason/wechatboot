package com.uncos.wechatboot.common;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * 用户分组
 * Created by xuwen on 2016-1-15.
 */
public class Group {

    private int id; // 分组id，由微信分配
    private String name; // 分组名字，UTF8编码
    private int count; // 分组内用户数量

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
