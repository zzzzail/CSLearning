package com.qingtian.lcpes.base.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangxq
 * @since 2022/9/2
 */
public class IPUtils {

    public static String getIp(HttpServletRequest request) {
        String ip = request.getHeader("X-Forwarded-For");
        if (org.apache.commons.lang3.StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)) {
            int index = ip.indexOf(",");
            return index != -1 ? ip.substring(0, index) : ip;
        }
        else {
            ip = request.getHeader("X-Real-IP");
            return StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip) ? ip : request.getRemoteAddr();
        }
    }
}
