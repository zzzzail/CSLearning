package com.qingtian.lcpes.base.util;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhangxq
 * @since 2022/9/5
 */
public class JsonUtil {

    private static ObjectMapper objectMapper = new CustomObjectMapper("");

    /**
     * 将 POJO 对象转为 JSON 字符串
     */
    public static <T> String toJson(T pojo) {
        String json;
        try {
            json = objectMapper.writeValueAsString(pojo);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    /**
     * 将 JSON 字符串转为 POJO 对象
     */
    public static <T> T fromJson(String json, Class<T> type) {
        T pojo;
        try {
            pojo = objectMapper.readValue(json, type);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return pojo;
    }

    /**
     * 对json字符串格式化输出
     *
     * @param jsonStr
     * @return
     */
    public static String formatJson(String jsonStr) {
        if (null == jsonStr || "".equals(jsonStr)) return "";
        StringBuilder sb = new StringBuilder();
        char last = '\0';
        char current = '\0';
        int indent = 0;
        for (int i = 0; i < jsonStr.length(); i++) {
            last = current;
            current = jsonStr.charAt(i);
            switch (current) {
                case '{':
                case '[':
                    sb.append(current);
                    sb.append('\n');
                    indent++;
                    addIndentBlank(sb, indent);
                    break;
                case '}':
                case ']':
                    sb.append('\n');
                    indent--;
                    addIndentBlank(sb, indent);
                    sb.append(current);
                    break;
                case ',':
                    sb.append(current);
                    if (last != '\\') {
                        sb.append('\n');
                        addIndentBlank(sb, indent);
                    }
                    break;
                default:
                    sb.append(current);
            }
        }

        return sb.toString();
    }

    /**
     * 添加space
     *
     * @param sb
     * @param indent
     */
    private static void addIndentBlank(StringBuilder sb, int indent) {
        for (int i = 0; i < indent; i++) {
            sb.append('\t');
        }
    }
}
