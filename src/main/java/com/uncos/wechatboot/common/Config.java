package com.uncos.wechatboot.common;

import com.uncos.wechatboot.base.AccountInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * wechatboot核心配置类
 * Created by xuwen on 2016/1/14.
 */
public class Config implements AccountInterface {

    private static Logger logger = LoggerFactory.getLogger(Config.class);
    private static final String configFile = "/wechatboot.properties";

    private String appid;
    private String secret;
    private String token;
    private String encodingAESKey;
    private String mchId;
    private String mchKey;

    private static Properties properties = new Properties();
    private static Config instance = new Config();

    private Config() {
        InputStream is = this.getClass().getResourceAsStream(configFile);
        if (is == null) {
            logger.warn("根目录下找不到wechatboot.properties文件");
            return;
        }
        try {
            properties.load(is);
            this.appid = getPropertyFromFile("wechatboot.appid");
            this.secret = getPropertyFromFile("wechatboot.secret");
            this.token = getPropertyFromFile("wechatboot.token");
            this.encodingAESKey = getPropertyFromFile("wechatboot.encodingAESKey");
            this.mchId = getPropertyFromFile("wechatboot.mchId");
            this.mchKey = getPropertyFromFile("wechatboot.mchKey");
        } catch (IOException e) {
            logger.warn("加载wechatboot.properties失败", e);
        }
    }

    public static Config instance() {
        return instance;
    }

    @Override
    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    @Override
    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getEncodingAESKey() {
        return encodingAESKey;
    }

    public void setEncodingAESKey(String encodingAESKey) {
        this.encodingAESKey = encodingAESKey;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getMchKey() {
        return mchKey;
    }

    public void setMchKey(String mchKey) {
        this.mchKey = mchKey;
    }

    /**
     * 从配置文件获取配置
     *
     * @param key
     * @return
     */
    private String getPropertyFromFile(String key) {
        String property = properties.getProperty(key);
        return property == null ? null : property.trim();
    }
}
