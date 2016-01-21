package com.uncos.wechatboot.api.js;

import com.uncos.wechatboot.common.JsApiParameter;
import com.uncos.wechatboot.utils.CredentialCenter;
import com.uncos.wechatboot.utils.RandomStringGenerator;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * JSSDK API
 * Created by xuwen on 2016/1/20.
 */
public class JsApi {

    private static JsApi instance = new JsApi();

    public static JsApi instance() {
        return instance;
    }

    /**
     * JSSDK URL签名
     *
     * @param url
     * @return
     */
    public JsApiParameter signatureUrl(String url) {
        String nonceStr = RandomStringGenerator.generate();
        String timeStamp = String.valueOf((System.currentTimeMillis() / 1000));
        Map<String, String> map = new HashMap<String, String>();
        map.put("jsapi_ticket", CredentialCenter.jsapiTicket());
        map.put("noncestr", nonceStr);
        map.put("timestamp", timeStamp);
        map.put("url", url);
        JsApiParameter jsApiParameter = new JsApiParameter();
        jsApiParameter.setNonceStr(nonceStr);
        jsApiParameter.setTimestamp(timeStamp);
        jsApiParameter.setSignature(signature(map));
        return jsApiParameter;
    }

    /**
     * 签名
     *
     * @param map
     * @return
     */
    private String signature(Map<String, String> map) {
        ArrayList<String> list = new ArrayList<String>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            list.add(entry.getKey() + "=" + entry.getValue() + "&");
        }
        int size = list.size();
        String[] arrayToSort = list.toArray(new String[size]);
        // 字典序
        Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
        StringBuilder signatureTemp = new StringBuilder();
        for (int i = 0; i < size; i++) {
            signatureTemp.append(arrayToSort[i]);
        }
        String signatureSource = signatureTemp.deleteCharAt(signatureTemp.length() - 1).toString();
        return DigestUtils.sha1Hex(signatureSource);
    }
}
