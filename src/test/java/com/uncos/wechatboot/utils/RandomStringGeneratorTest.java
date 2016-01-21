package com.uncos.wechatboot.utils;

import org.junit.Test;

/**
 * Created by xuwen on 2016-1-21.
 */
public class RandomStringGeneratorTest {

    @Test
    public void testRandomString(){
        System.out.println("测试 获取随机字符串" + RandomStringGenerator.generate());
    }

    @Test
    public void testUUID(){
        System.out.println("测试 获取UUID " + RandomStringGenerator.uuidWithoutSymbol());
    }
}
