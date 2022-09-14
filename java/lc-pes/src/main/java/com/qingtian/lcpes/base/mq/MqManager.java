package com.qingtian.lcpes.base.mq;

import cn.hutool.core.util.StrUtil;
import com.qingtian.lcpes.base.mq.rabbitmq.RabbitMQMessageReceiver;
import com.qingtian.lcpes.base.mq.rabbitmq.RabbitMQMessageReceiverProxy;
import com.qingtian.lcpes.base.mq.rabbitmq.RabbitMQMessageSender;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangxq
 * @since 2022/9/1
 */
@Component
@ConditionalOnProperty("qingtian.mq.enable")
@Slf4j
public class MqManager {

    /* 存储 Sender 的 map */
    private static final Map<String, MessageSender> senderMap = new ConcurrentHashMap<>();

    /* 存储 Receiver 的 map */
    private static final Map<String, MessageReceiver> receiverMap = new ConcurrentHashMap<>();

    @Autowired
    private Connection rabbitMQConnection;

    @Autowired
    private MQConfigurationProperties mqConfigurationProperties;

    /* 获取 Sender */
    public MessageSender getSender(String senderKey) {
        return senderMap.getOrDefault(senderKey, null);
    }

    /* 获取 Receiver */
    public MessageReceiver getReceiver(String receiverKey) {
        return receiverMap.getOrDefault(receiverKey, null);
    }

    public boolean registerRabbitSender(String senderKey, String exchangeName, String queueName, String routingKey, String exchangeType) {
        return registerRabbitSender(senderKey, exchangeName, queueName, routingKey, exchangeType, false);
    }

    public boolean registerRabbitSender(String senderKey, String exchangeName, String queueName, String routingKey, String exchangeType, boolean sendConfirm) {
        if (StrUtil.isEmpty(senderKey)) {
            throw new IllegalArgumentException("senderKey不能为空");
        }
        boolean res = false;
        try {
            RabbitMQMessageSender sender = new RabbitMQMessageSender(exchangeName, queueName, routingKey, exchangeType, sendConfirm);
            sender.init(rabbitMQConnection);
            senderMap.put(senderKey, sender);
            res = true;
        }
        catch (Exception e) {
            log.error("RabbitMQ connection fail.", e);
        }
        return res;
    }

    public boolean registerRabbitReceiver(String queueName, String receiverKey, MessageReceiver receiver, boolean autoAck) {
        boolean res = false;
        if (StrUtil.isEmpty(queueName)) {
            throw new IllegalArgumentException("queueName 不能为空");
        }
        if (StrUtil.isEmpty(receiverKey)) {
            throw new IllegalArgumentException("receiverKey 不能为空");
        }

        try {
            RabbitMQMessageReceiverProxy proxy = new RabbitMQMessageReceiverProxy(queueName, receiver, autoAck);
            proxy.init(rabbitMQConnection, mqConfigurationProperties.getRabbitmq());
            receiverMap.put(receiverKey, receiver);
            res = true;
        }
        catch (Exception e) {
            log.error("", e);
        }
        return res;
    }

}
