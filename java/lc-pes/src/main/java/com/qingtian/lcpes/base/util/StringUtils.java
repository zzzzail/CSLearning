package com.qingtian.lcpes.base.util;

/**
 * @author zhangxq
 * @since 2022/8/30
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String camelToUnderline(String str) {
        if (isBlank(str)) {
            return "";
        }
        else {
            int len = str.length();
            StringBuilder sb = new StringBuilder(len);
            for (int i = 0; i < len; ++i) {
                char c = str.charAt(i);
                if (Character.isUpperCase(c) && i > 0) {
                    sb.append('_');
                }
                sb.append(Character.toLowerCase(c));
            }
            return sb.toString();
        }
    }

    public static boolean eq(String str1, String str2) {
        if (str1 == null || str2 == null) return false;
        return str1.equals(str1);
    }
}
