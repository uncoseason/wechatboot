package com.uncos.wechatboot.utils;

import com.uncos.wechatboot.api.Wechatboot;
import com.uncos.wechatboot.exception.MessageCryptException;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * 消息加密、解密
 * Created by xuwen on 2016/1/19.
 */
public class MessageCrypt {

    private static Logger logger = LoggerFactory.getLogger(MessageCrypt.class);

    public static final Base64 base64 = new Base64();

    private String currentEncodingAESKey;
    private int decodeCount = 0; // 解密次数

    /**
     * 消息加密
     *
     * @param data
     * @return
     */
    public String encodeAES(String data) {
        byte[] aesKey = Base64.decodeBase64(currentEncodingAESKey + "=");
        String randomStr = RandomStringGenerator.generate(16);
        ByteGroup byteCollector = new ByteGroup();
        byte[] randomStrBytes = randomStr.getBytes(Wechatboot.charset());
        byte[] textBytes = data.getBytes(Wechatboot.charset());
        byte[] networkBytesOrder = getNetworkBytesOrder(textBytes.length);
        byte[] appidBytes = Wechatboot.config().getAppid().getBytes(Wechatboot.charset());

        // randomStr + networkBytesOrder + text + appid
        byteCollector.addBytes(randomStrBytes);
        byteCollector.addBytes(networkBytesOrder);
        byteCollector.addBytes(textBytes);
        byteCollector.addBytes(appidBytes);

        // ... + pad: 使用自定义的填充方式对明文进行补位填充
        byte[] padBytes = encodePKCS7(byteCollector.size());
        byteCollector.addBytes(padBytes);

        // 获得最终的字节流, 未加密
        byte[] unencrypted = byteCollector.getBytes();

        try {
            // 设置加密模式为AES的CBC模式
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(aesKey, 0, 16);
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, iv);

            // 加密
            byte[] encrypted = cipher.doFinal(unencrypted);

            // 使用BASE64对加密后的字符串进行编码
            String base64Encrypted = base64.encodeToString(encrypted);

            return base64Encrypted;
        } catch (Exception e) {
            throw new MessageCryptException("消息加密失败");
        }
    }

    /**
     * 消息解密
     *
     * @param data
     * @return
     */
    public String decodeAES(String data) {
        String[] encodingAESKeys = Wechatboot.config().getEncodingAESKey().split(",");
        while (decodeCount < encodingAESKeys.length) {
            currentEncodingAESKey = encodingAESKeys[decodeCount++];
            try {
                return decodeAES(data, currentEncodingAESKey);
            } catch (MessageCryptException e) {
                if (decodeCount > encodingAESKeys.length) {
                    throw e;
                }
                logger.info("消息解密失败，使用下一组encodingAESKey重试，重试次数：{}", decodeCount);
            }
        }
        throw new MessageCryptException("消息解密失败，解密次数：" + decodeCount);
    }

    /**
     * 消息解密
     *
     * @param data
     * @param encodingAESKey
     * @return
     */
    private String decodeAES(String data, String encodingAESKey) {
        byte[] aesKey = Base64.decodeBase64(encodingAESKey + "=");
        byte[] original;
        try {
            // 设置解密模式为AES的CBC模式
            Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
            SecretKeySpec key_spec = new SecretKeySpec(aesKey, "AES");
            IvParameterSpec iv = new IvParameterSpec(Arrays.copyOfRange(aesKey, 0, 16));
            cipher.init(Cipher.DECRYPT_MODE, key_spec, iv);

            // 使用BASE64对密文进行解码
            byte[] encrypted = Base64.decodeBase64(data);

            // 解密
            original = cipher.doFinal(encrypted);
        } catch (Exception e) {
            throw new MessageCryptException("消息解密失败");
        }

        String xmlContent, from_appid;
        try {
            // 去除补位字符
            byte[] bytes = decodePKCS7(original);

            // 分离16位随机字符串,网络字节序和AppId
            byte[] networkOrder = Arrays.copyOfRange(bytes, 16, 20);

            int xmlLength = recoverNetworkBytesOrder(networkOrder);

            xmlContent = new String(Arrays.copyOfRange(bytes, 20, 20 + xmlLength), Wechatboot.charset());
            from_appid = new String(Arrays.copyOfRange(bytes, 20 + xmlLength, bytes.length),
                    Wechatboot.charset());
        } catch (Exception e) {
            throw new MessageCryptException("消息解密失败");
        }

        // appid不相同的情况
        if (!from_appid.equals(Wechatboot.config().getAppid())) {
            throw new MessageCryptException("appid不匹配");
        }
        return xmlContent;
    }

    /**
     * 生成4个字节的网络字节序
     *
     * @param sourceNumber
     * @return
     */
    private byte[] getNetworkBytesOrder(int sourceNumber) {
        byte[] orderBytes = new byte[4];
        orderBytes[3] = (byte) (sourceNumber & 0xFF);
        orderBytes[2] = (byte) (sourceNumber >> 8 & 0xFF);
        orderBytes[1] = (byte) (sourceNumber >> 16 & 0xFF);
        orderBytes[0] = (byte) (sourceNumber >> 24 & 0xFF);
        return orderBytes;
    }

    /**
     * 还原4个字节的网络字节序
     *
     * @param orderBytes
     * @return
     */
    private int recoverNetworkBytesOrder(byte[] orderBytes) {
        int sourceNumber = 0;
        for (int i = 0; i < 4; i++) {
            sourceNumber <<= 8;
            sourceNumber |= orderBytes[i] & 0xff;
        }
        return sourceNumber;
    }

    /**
     * PKCS7编码
     *
     * @param count
     * @return
     */
    private byte[] encodePKCS7(int count) {
        int BLOCK_SIZE = 32;
        // 计算需要填充的位数
        int amountToPad = BLOCK_SIZE - (count % BLOCK_SIZE);
        if (amountToPad == 0) {
            amountToPad = BLOCK_SIZE;
        }
        // 获得补位所用的字符
        byte target = (byte) (amountToPad & 0xFF);
        char padChr = (char) target;
        String tmp = new String();
        for (int index = 0; index < amountToPad; index++) {
            tmp += padChr;
        }
        return tmp.getBytes(Wechatboot.charset());
    }

    /**
     * PKCS7解码
     *
     * @param decrypted
     * @return
     */
    private byte[] decodePKCS7(byte[] decrypted) {
        int pad = (int) decrypted[decrypted.length - 1];
        if (pad < 1 || pad > 32) {
            pad = 0;
        }
        return Arrays.copyOfRange(decrypted, 0, decrypted.length - pad);
    }

    /**
     * ByteGroup
     */
    private class ByteGroup {
        ArrayList<Byte> byteContainer = new ArrayList<Byte>();

        public byte[] getBytes() {
            byte[] bytes = new byte[byteContainer.size()];
            for (int i = 0; i < byteContainer.size(); i++) {
                bytes[i] = byteContainer.get(i);
            }
            return bytes;
        }

        public ByteGroup addBytes(byte[] bytes) {
            for (byte b : bytes) {
                byteContainer.add(b);
            }
            return this;
        }

        public int size() {
            return byteContainer.size();
        }
    }
}
