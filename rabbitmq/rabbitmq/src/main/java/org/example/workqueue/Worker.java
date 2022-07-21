package org.example.workqueue;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.example.util.RabbitMQUtils;

import java.io.IOException;

/**
 * @author zail
 * @date 2022/6/28
 */
public class Worker {
    
    private static final String QUEUE_NAME = "hello";
    
    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtils.getChannel();
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String receivedMessage = new String(delivery.getBody());
            System.out.println("接收到消息:" + receivedMessage);
            
            // 消息应答
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        CancelCallback cancelCallback = (consumerTag) -> {
            System.out.println(consumerTag + "消费者取消消费接口回调逻辑");
        };
        
        System.out.println("Worker 消费者启动等待消费 ..................");
        /*
           消费者设置位不公平分发消息模式
           QOS（Quality of Service）服务质量
           prefetchCount 预取消息的数量（服务器交付的最大消息数量，0 表示不限制）
         */
        channel.basicQos(1);
        channel.basicConsume(
                QUEUE_NAME,
                false,
                deliverCallback,
                cancelCallback);
    }
}
