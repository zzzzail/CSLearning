package com.qingtian.lcpes.base.mq;

import lombok.Data;

import java.util.HashMap;

/**
 * @author zhangxq
 * @since 2022/9/1
 */
@Data
public class Message<T> {

    /* 消息头 */
    private HashMap<String, String> header;

    /* 消息体 */
    private T body;

    /**
     * 获取消息的方法
     */
    public String getMessage() {
        return body.toString();
    }

}
