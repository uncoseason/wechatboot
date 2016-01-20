package com.uncos.wechatboot.api.material;

import com.uncos.wechatboot.api.material.protocol.get_material_count.GetMaterialCountRequest;
import com.uncos.wechatboot.api.material.protocol.get_material_count.GetMaterialCountResponse;
import com.uncos.wechatboot.api.material.protocol.media_get.MediaGetRequest;
import com.uncos.wechatboot.api.material.protocol.media_get.MediaGetResponse;
import com.uncos.wechatboot.api.material.protocol.media_upload.MediaUploadRequest;
import com.uncos.wechatboot.api.material.protocol.media_upload.MediaUploadResponse;
import com.uncos.wechatboot.common.MaterialType;
import com.uncos.wechatboot.exception.WechatException;
import com.uncos.wechatboot.utils.Checker;
import com.uncos.wechatboot.utils.Converter;
import com.uncos.wechatboot.utils.Http;
import com.uncos.wechatboot.utils.RandomStringGenerator;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 素材API
 * Created by xuwen on 2016/1/17.
 */
public class MaterialApi {

    /*新增临时素材*/
    private static final String API_MEDIA_UPLOAD = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
    /*获取临时素材*/
    private static final String API_MEDIA_GET = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
    /*获取素材总数*/
    private static final String API_GET_MATERIAL_COUNT = "https://api.weixin.qq.com/cgi-bin/material/get_materialcount?access_token=ACCESS_TOKEN";

    private static MaterialApi instance = new MaterialApi();

    public static MaterialApi instance() {
        return instance;
    }

    /**
     * 新增临时素材
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public MediaUploadResponse mediaUpload(MediaUploadRequest request) throws WechatException {
        String result = Http.postFile(Http.buildObjectParamURL(API_MEDIA_UPLOAD, request), request.getFile(), "media");
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, MediaUploadResponse.class);
    }

    /**
     * 新增临时素材
     *
     * @param type 素材类型
     * @param file 素材
     * @return
     * @throws WechatException
     * @throws IOException
     */
    public MediaUploadResponse mediaUpload(MaterialType type, File file) throws WechatException, IOException {
        MediaUploadRequest request = new MediaUploadRequest();
        request.setType(type);
        request.setFile(file);
        return mediaUpload(request);
    }

    /**
     * 获取临时素材
     *
     * @param request
     * @return
     * @throws WechatException
     * @throws IOException
     */
    public MediaGetResponse mediaGet(MediaGetRequest request) throws WechatException, IOException {
        File file = File.createTempFile(RandomStringGenerator.generate(), null);
        InputStream is = Http.getFile(API_MEDIA_GET, request);
        FileUtils.copyInputStreamToFile(is, file);
        MediaGetResponse response = new MediaGetResponse();
        response.setFile(file);
        return response;
    }

    /**
     * 获取临时素材
     *
     * @param mediaId 媒体文件ID/素材ID
     * @return
     * @throws WechatException
     * @throws IOException
     */
    public MediaGetResponse mediaGet(String mediaId) throws WechatException, IOException {
        MediaGetRequest request = new MediaGetRequest();
        request.setMediaId(mediaId);
        return mediaGet(request);
    }

    /**
     * 获取素材总数
     *
     * @param request
     * @return
     * @throws WechatException
     */
    public GetMaterialCountResponse getMaterialCount(GetMaterialCountRequest request) throws WechatException {
        String result = Http.get(API_GET_MATERIAL_COUNT, request);
        Checker.checkApiResponse(result);
        return Converter.fromJSON(result, GetMaterialCountResponse.class);
    }

    /**
     * 获取素材总数
     *
     * @return
     * @throws WechatException
     */
    public GetMaterialCountResponse getMaterialCount() throws WechatException {
        GetMaterialCountRequest request = new GetMaterialCountRequest();
        return getMaterialCount(request);
    }
}
