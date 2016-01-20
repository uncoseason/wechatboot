package com.uncos.wechatboot.utils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.uncos.wechatboot.api.Wechatboot;
import com.uncos.wechatboot.common.RequestType;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * HTTP工具类
 * Created by xuwen on 2016/1/14.
 */
public class Http {

    private static Logger logger = LoggerFactory.getLogger(Http.class);

    /**
     * post请求
     *
     * @param url         请求地址
     * @param inputStream 请求数据
     * @return 响应数据
     */
    public static String post(String url, InputStream inputStream) {
        try {
            HttpEntity entity = Request.Post(url)
                    .bodyStream(inputStream, ContentType.APPLICATION_OCTET_STREAM)
                    .execute().returnResponse().getEntity();
            String res = entity != null ? EntityUtils.toString(entity, Wechatboot.charset()) : null;
            logger.debug("http post : {}\n响应数据\n{}", url, res);
            return res;
        } catch (Exception e) {
            throw new RuntimeException("post请求异常", e);
        }
    }

    /**
     * post请求
     *
     * @param url 请求地址
     * @return 返回的数据
     */
    public static String post(String url) {
        return post(url, new ByteArrayInputStream(new byte[0]));
    }

    /**
     * post请求
     *
     * @param url  请求地址
     * @param data post的数据
     * @return 返回的数据
     */
    public static String post(String url, String data) {
        try {
            HttpEntity entity = Request.Post(url)
                    .bodyString(data, ContentType.APPLICATION_OCTET_STREAM)
                    .execute().returnResponse().getEntity();
            String res = entity != null ? EntityUtils.toString(entity, Wechatboot.charset()) : null;
            logger.debug("http post : {} \n请求数据\n{}\n响应数据\n{}", url, data, res);
            return res;
        } catch (Exception e) {
            throw new RuntimeException("post请求异常", e);
        }
    }

    /**
     * post上传文件
     *
     * @param url      请求地址
     * @param file     post的文件
     * @param fileName post的文件名
     * @return
     */
    public static String postFile(String url, File file, String fileName) {
        try {
            HttpEntity reqEntity = MultipartEntityBuilder.create().addBinaryBody(fileName, file).build();
            Request request = Request.Post(url);
            request.body(reqEntity);
            HttpEntity resEntity = request.execute().returnResponse().getEntity();
            String res = resEntity != null ? EntityUtils.toString(resEntity, Wechatboot.charset()) : null;
            logger.debug("http post : {}\n响应数据\n{}", url, res);
            return res;
        } catch (Exception e) {
            throw new RuntimeException("post with file请求异常", e);
        }
    }

    /**
     * post请求
     *
     * @param url  请求地址
     * @param file post的文件
     * @return 返回的数据
     */
    public static String postFile(String url, File file) {
        return postFile(url, file, null);
    }

    /**
     * post请求
     *
     * @param url         请求地址
     * @param object      请求对象
     * @param requestType 请求类型
     * @return 返回的数据
     */
    public static String post(String url, Object object, RequestType requestType) {
        if (requestType == null) {
            throw new RuntimeException("必须设定请求类型");
        }
        switch (requestType) {
            case JSON:
                return post(url, Converter.toJSON(object));
            case XML:
                return post(url, Converter.toXML(object));
            default:
                throw new RuntimeException("未知的请求类型");
        }
    }

    /**
     * get请求
     *
     * @param url 请求地址
     * @return 返回的数据
     */
    private static String get(String url) {
        try {
            HttpEntity entity = Request.Get(url).
                    execute().returnResponse().getEntity();
            String res = entity != null ? EntityUtils.toString(entity, Wechatboot.charset()) : null;
            logger.debug("http get : {}\n响应数据\n{}", url, res);
            return res;
        } catch (Exception e) {
            throw new RuntimeException("get请求失败", e);
        }
    }

    /**
     * get
     *
     * @param url    请求地址
     * @param params 请求对象
     * @return 返回的数据
     */
    public static String get(String url, Object params) {
        StringBuilder requestURL = new StringBuilder(url);
        return get(buildObjectParamURL(url, params));
    }

    /**
     * get下载文件
     *
     * @param url    请求地址
     * @param params 参数对象
     * @return 文件的二进制流
     */
    public static InputStream getFile(String url, Object params) {
        StringBuilder requestURL = new StringBuilder(url);
        try {
            Request request = Request.Get(buildObjectParamURL(url, params));
            HttpEntity resEntity = request.execute().returnResponse().getEntity();
            logger.debug("http get file : {}", requestURL.toString());
            return resEntity.getContent();
        } catch (Exception e) {
            throw new RuntimeException("get请求失败", e);
        }
    }

    /**
     * get下载文件
     *
     * @param url 请求地址
     * @return 文件的二进制流
     */
    public static InputStream getFile(String url) {
        return getFile(url, null);
    }

    /**
     * 为URL附加对象的所有属性值作为参数
     *
     * @param url    请求地址
     * @param params 参数对象
     */
    public static String buildObjectParamURL(String url, Object params) {
        if (params == null) {
            return url;
        }
        StringBuilder requestURL = new StringBuilder(url);
        // 截掉参数
        int index = requestURL.indexOf("?");
        if (index != -1) {
            requestURL.delete(index, requestURL.length());
        }
        // 附加所有属性
        requestURL.append("?");
        Class<?> clazz = params.getClass();
        do {
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(JsonIgnore.class)) {
                    continue;
                }
                String fieldName = field.getName();
                String fieldValue = null;
                JsonProperty jsonProerty;
                try {
                    Method getMethod = clazz.getDeclaredMethod("get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1));
                    if (getMethod != null) {
                        if (getMethod.isAnnotationPresent(JsonIgnore.class)) {
                            continue;
                        }
                        jsonProerty = getMethod.getAnnotation(JsonProperty.class);
                    } else {
                        jsonProerty = field.getAnnotation(JsonProperty.class);
                    }
                    if (jsonProerty != null) {
                        fieldName = jsonProerty.value();
                    }
                } catch (NoSuchMethodException e) {
                    // skip this
                }
                field.setAccessible(true);
                try {
                    Object value = field.get(params);
                    if (value == null) {
                        continue;
                    } else {
                        fieldValue = value.toString();
                    }
                } catch (IllegalAccessException e) {
                    // never throws
                }
                requestURL.append(fieldName).append("=").append(Converter.toURLEncode(fieldValue)).append("&");
            }
        } while ((clazz = clazz.getSuperclass()) != Object.class);
        requestURL.deleteCharAt(requestURL.length() - 1);
        return requestURL.toString();
    }
}
