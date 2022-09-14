package com.qingtian.lcpes.base.mq.rabbitmq;

import lombok.Data;

/**
 * @author zhangxq
 * @since 2022/9/1
 */
@Data
public class RabbitMQConfigurationProperties {

    /* 是否开启 */
    private boolean enable = true;
    /* 连接地址 */
    private String host = "127.0.0.1";
    /* 用户名 */
    private String username = "guest";
    /* 密码 */
    private String password = "guest";
    /* 虚拟地址 */
    private String virtualHost = "/";
    /* 拉取消息数量 */
    private int prefetchSize = 0;
    /* 拉取消息总数 */
    private int prefetchCount = 30;
    /* 是否全局 */
    private boolean global = false;

}
