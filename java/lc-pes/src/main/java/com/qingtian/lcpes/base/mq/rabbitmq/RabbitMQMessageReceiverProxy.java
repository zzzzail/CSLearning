package com.qingtian.lcpes.base.mq.rabbitmq;

import com.qingtian.lcpes.base.mq.Message;
import com.qingtian.lcpes.base.mq.MessageReceiver;
import com.qingtian.lcpes.base.mq.StringMessage;
import com.rabbitmq.client.*;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeoutException;

/**
 * @author zhangxq
 * @since 2022/9/1
 */
@Slf4j
public class RabbitMQMessageReceiverProxy {
    private String queueName;
    private MessageReceiver messageReceiver;
    private Channel channel = null;
    private boolean autoAck = false;

    public RabbitMQMessageReceiverProxy(String queueName, MessageReceiver messageReceiver, boolean autoAck) {
        this.queueName = queueName;
        this.messageReceiver = messageReceiver;
        this.autoAck = autoAck;
    }

    public void init(Connection connection, RabbitMQConfigurationProperties properties) {
        try {
            channel = connection.createChannel();
            Consumer consumer = new DefaultConsumer(channel) {
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                    String message = new String(body, StandardCharsets.UTF_8);
                    log.info("接受消息为：" + message);
                    StringMessage msg = new StringMessage();
                    msg.setBody(message);
                    messageReceiver.receive(msg);
                }
            };
            channel.basicConsume(queueName, autoAck, consumer);
            channel.basicQos(properties.getPrefetchSize(), properties.getPrefetchCount(), properties.isGlobal());
        }
        catch (IOException e) {
            throw new IllegalStateException("inti RabbitMessageSender exception: " + e.getMessage(), e);
        }
    }
}
