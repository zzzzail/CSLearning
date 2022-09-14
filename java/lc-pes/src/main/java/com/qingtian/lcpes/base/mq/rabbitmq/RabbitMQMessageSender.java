package com.qingtian.lcpes.base.mq.rabbitmq;

import com.qingtian.lcpes.base.mq.Message;
import com.qingtian.lcpes.base.mq.MessageSender;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangxq
 * @since 2022/9/1
 */
@Slf4j
public class RabbitMQMessageSender implements MessageSender {
    private Channel channel;
    private final String exchangeName;
    private final String queueName;
    private final String routingKey;
    private final String exchangeType;
    private boolean sendConfirm;

    public RabbitMQMessageSender(String exchangeName, String queueName, String routingKey, String exchangeType, boolean sendConfirm) {
        this.exchangeName = exchangeName;
        this.queueName = queueName;
        this.routingKey = routingKey;
        this.exchangeType = exchangeType;
        this.sendConfirm = sendConfirm;
    }

    public void init(Connection connection) {
        try {
            this.channel = connection.createChannel();
            if (sendConfirm) {
                channel.confirmSelect();
            }
            channel.exchangeDeclare(exchangeName, exchangeType, true, false, false, null);
            channel.queueDeclare(queueName, true, false, false, null);
            channel.queueBind(queueName, exchangeName, routingKey, null);
        }
        catch (IOException e) {
            throw new IllegalArgumentException("init RabbitMQMessageSender exception: " + e.getMessage(), e);
        }
    }

    @Override
    public boolean send(Message<?> message) {
        boolean res = true;
        try {
            String msg = message.getMessage();
            log.info("发送消息为{}", msg);
            // todo 处理 message 中的 header
            this.channel.basicPublish(exchangeName, routingKey, null, msg.getBytes(StandardCharsets.UTF_8));
            if (sendConfirm) {
                res = channel.waitForConfirms();
                return res;
            }
            else {
                return res;
            }
        }
        catch (Exception e) {
            throw new IllegalStateException("发送消息异常:" + e.getMessage(), e);
        }
    }

}
