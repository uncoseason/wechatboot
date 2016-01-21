package com.uncos.wechatboot.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Date工具
 * Created by xuwen on 2016-1-21.
 */
public class DateUtils {

    /**
     * 获取时间戳
     *
     * @param date
     * @return
     */
    public static long getTimestamp(Date date) {
        return date.getTime();
    }

    /**
     * 获取时间戳
     *
     * @return
     */
    public static long getTimestamp() {
        return getTimestamp(new Date());
    }

    /**
     * 获取微信规定的时间戳
     *
     * @param date
     * @return
     */
    public static String getWechatTimestamp(Date date) {
        return String.valueOf(getTimestamp(date) / 1000);
    }

    /**
     * 获取微信规定的时间戳
     *
     * @return
     */
    public static String getWechatTimestamp() {
        return getWechatTimestamp(new Date());
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param format
     * @return
     */
    public static String format(Date date, String format) {
        return new SimpleDateFormat(format).format(date);
    }

    /**
     * 日期格式化
     *
     * @param format
     * @return
     */
    public static String format(String format) {
        return format(new Date(), format);
    }
}
