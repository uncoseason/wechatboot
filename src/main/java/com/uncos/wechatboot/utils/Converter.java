package com.uncos.wechatboot.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.apache.http.Consts;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * JSON、XML转换器
 * Created by xuwen on 2016-1-6.
 */
public class Converter {

    private static ObjectMapper objectMapper = new ObjectMapper();
    private static XmlMapper xmlMapper = new XmlMapper();

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        xmlMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        xmlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 对象转JSON
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String toJSON(T object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("write json error", e);
        }
    }

    /**
     * JSON转对象
     *
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJSON(String json, Class<T> clazz) {
        try {
            return objectMapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new RuntimeException("read json error", e);
        }
    }

    /**
     * JSON转XML
     *
     * @param object
     * @param <T>
     * @return
     */
    public static <T> String toXML(T object) {
        try {
            JacksonXmlRootElement jacksonXmlRootElement = object.getClass().getAnnotation(JacksonXmlRootElement.class);
            return xmlMapper.writer().withRootName(jacksonXmlRootElement != null ? jacksonXmlRootElement.localName() : "xml").writeValueAsString(object).replace(" xmlns=\"\"","");
        } catch (JsonProcessingException e) {
            throw new RuntimeException("write xml error", e);
        }
    }

    /**
     * XML转对象
     *
     * @param xml
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromXML(String xml, Class<T> clazz) {
        try {
            return xmlMapper.readValue(xml, clazz);
        } catch (IOException e) {
            throw new RuntimeException("read xml error", e);
        }
    }

    /**
     * 使用UTF-8进行URL编码
     *
     * @param str
     * @return
     */
    public static String toURLEncode(String str) {
        String result = null;
        try {
            result = URLEncoder.encode(str, Consts.UTF_8.name());
        } catch (UnsupportedEncodingException e) {
            // never throws
        }
        return result;
    }

}
