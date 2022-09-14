package com.qingtian.lcpes.base.mq;

import com.qingtian.lcpes.base.mq.rabbitmq.RabbitMQConfigurationProperties;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author zhangxq
 * @since 2022/9/1
 */
@Component
@ConfigurationProperties(prefix = "qingtian.mq")
@Data
@Slf4j
public class MQConfigurationProperties {

    /* 是否开启消息队列 */
    private boolean enable = true;

    /* RabbitMQ 配置 */
    private RabbitMQConfigurationProperties rabbitmq;

}
