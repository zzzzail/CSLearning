package com.qingtian.lcpes.base.mq;

import cn.hutool.core.util.StrUtil;
import com.qingtian.lcpes.base.exception.QTBusinessException;
import com.qingtian.lcpes.base.mq.rabbitmq.RabbitMQConfigurationProperties;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangxq
 * @since 2022/9/1
 */
@Configuration
@ConditionalOnProperty("qingtian.mq.enable")
@Slf4j
public class MQConfiguration {

    @Autowired
    private MQConfigurationProperties mqConfigurationProperties;

    @Bean
    @ConditionalOnProperty("qingtian.mq.rabbitmq.enable")
    public Connection rabbitMQConnection() {
        RabbitMQConfigurationProperties rabbitmqProperties = mqConfigurationProperties.getRabbitmq();
        validateRabbitArg(rabbitmqProperties);

        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost(rabbitmqProperties.getHost());
        connectionFactory.setUsername(rabbitmqProperties.getUsername());
        connectionFactory.setPassword(rabbitmqProperties.getPassword());
        connectionFactory.setVirtualHost(rabbitmqProperties.getVirtualHost());
        Connection connection = null;
        try {
            connection = connectionFactory.newConnection();
        }
        catch (IOException | TimeoutException e) {
            e.printStackTrace();
            throw new QTBusinessException("RabbitMQ 连接失败！");
        }
        return connection;
    }

    private void validateRabbitArg(RabbitMQConfigurationProperties properties) {
        if (StrUtil.isEmpty(properties.getHost())) {
            throw new IllegalArgumentException("qingtian.mq.rabbitmq.host property can not be null");
        }
        else if (StrUtil.isEmpty(properties.getVirtualHost())) {
            throw new IllegalArgumentException("qingtian.mq.rabbitmq.virtualHost property can not be null");
        }
        else if (StrUtil.isEmpty(properties.getUsername())) {
            throw new IllegalArgumentException("qingtian.mq.rabbitmq.username property can not be null");
        }
        else if (StrUtil.isEmpty(properties.getPassword())) {
            throw new IllegalArgumentException("qingtian.mq.rabbitmq.password property can not be null");
        }
    }

}
