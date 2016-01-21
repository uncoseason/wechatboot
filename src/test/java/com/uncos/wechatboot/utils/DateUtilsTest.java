package com.uncos.wechatboot.utils;

import org.junit.Test;

/**
 * Created by xuwen on 2016-1-21.
 */
public class DateUtilsTest {

    @Test
    public void testTimestamp(){
        System.out.println("测试 获取微信规定的时间戳" + DateUtils.getWechatTimestamp());
    }

    @Test
    public void testFormat(){
        System.out.println("测试 格式化事件" + DateUtils.format("yyyyMMddHHmmss"));
    }
}
