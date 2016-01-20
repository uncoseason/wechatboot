package com.uncos.wechatboot.utils.converter;

import com.uncos.wechatboot.utils.Converter;
import org.junit.Before;
import org.junit.Test;

/**
 * Created by xuwen on 2016-1-6.
 */
public class ConverterTest {

    private Son son;

    @Before
    public void before() {
        son = new Son();
        son.setParentField1("p1");
        son.setParentField2(12);
        son.setSonField1("s1");
        son.setSonField2(22);
    }

    @Test
    public void testToJSON() {
        System.out.println(Converter.toJSON(son));
    }

    @Test
    public void testFromJson() {
        System.out.println(Converter.fromJSON("{\"parentField2\":12,\"sonField2\":22,\"p1\":\"p1\",\"s1\":\"s1\"}", Son.class));
    }

    @Test
    public void testToXML() {
        System.out.println(Converter.toXML(son));
    }

    @Test
    public void testFromXML() {
        System.out.println(Converter.fromXML("<xml xmlns=\"\"><parentField2>12</parentField2><sonField2>22</sonField2><p1><![CDATA[p1]]></p1><s1><![CDATA[s1]]></s1></xml>", Son.class));
    }

}
