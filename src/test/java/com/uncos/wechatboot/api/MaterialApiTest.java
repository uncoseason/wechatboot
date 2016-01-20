package com.uncos.wechatboot.api;

import com.uncos.wechatboot.api.material.protocol.get_material_count.GetMaterialCountResponse;
import com.uncos.wechatboot.api.material.protocol.media_get.MediaGetResponse;
import com.uncos.wechatboot.api.material.protocol.media_upload.MediaUploadResponse;
import com.uncos.wechatboot.common.MaterialType;
import org.junit.Test;

import java.io.File;

/**
 * Created by xuwen on 2016/1/17.
 */
public class MaterialApiTest {

    @Test
    public void testMediaUpload() throws Exception {
        Wechatboot.materialApi().mediaUpload(MaterialType.thumb, new File(MaterialApiTest.class.getClassLoader().getResource("test.jpg").toURI()));
        System.out.println("测试 新增临时素材");
    }

    @Test
    public void testMediaGet() throws Exception {
        MediaUploadResponse mediaUploadResponse = Wechatboot.materialApi().mediaUpload(MaterialType.image, new File(MaterialApiTest.class.getClassLoader().getResource("test.jpg").toURI()));
        MediaGetResponse mediaGetResponse = Wechatboot.materialApi().mediaGet(mediaUploadResponse.getMediaId());
        System.out.println("测试 获取临时素材" + mediaGetResponse.getFile().getAbsolutePath());
    }

    @Test
    public void testMediaGetThumb() throws Exception {
        MediaUploadResponse mediaUploadResponse = Wechatboot.materialApi().mediaUpload(MaterialType.thumb, new File(MaterialApiTest.class.getClassLoader().getResource("test.jpg").toURI()));
        MediaGetResponse mediaGetResponse = Wechatboot.materialApi().mediaGet(mediaUploadResponse.getThumbMediaId());
        System.out.println("测试 获取临时素材" + mediaGetResponse.getFile().getAbsolutePath());
    }

    @Test
    public void testGetMaterialCount() throws Exception {
        GetMaterialCountResponse materialCountResponse = Wechatboot.materialApi().getMaterialCount();
        System.out.println("测试 获取素材总数");
    }
}
