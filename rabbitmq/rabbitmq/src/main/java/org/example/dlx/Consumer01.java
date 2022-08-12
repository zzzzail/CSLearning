package org.example.dlx;

import com.rabbitmq.client.*;
import org.example.util.RabbitMQUtils;

import java.io.IOException;

/**
 * @author zail
 * @date 2022/8/2
 */
public class Consumer01 {
    
    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtils.getChannel();
        System.out.println("等待接收消息........... ");
        // DeliverCallback deliverCallback = (consumerTag, delivery) -> {
        //     String message = new String(delivery.getBody(), "UTF-8");
        //     System.out.println("Consumer01 接收到消息" + message);
        //     String fromExchange = delivery.getProperties().getHeaders().get("x-first-death-exchange").toString();
        //     String fromQueue = delivery.getProperties().getHeaders().get("x-first-death-queue").toString();
        //     String reason = delivery.getProperties().getHeaders().get("x-first-death-reason").toString();
        //     System.out.println("死信消息来自 exchange：" + fromExchange);
        //     System.out.println("死信消息来自 queue：" + fromQueue);
        //     System.out.println("死信消息产生的原因：" + reason);
        // };
        // channel.basicConsume(Producer.DEAD_QUEUE, true, deliverCallback, consumerTag -> {});
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("Consumer01 接收到消息" + message);
                
                String fromExchange = properties.getHeaders().get("x-first-death-exchange").toString();
                String fromQueue = properties.getHeaders().get("x-first-death-queue").toString();
                String reason = properties.getHeaders().get("x-first-death-reason").toString();
                System.out.println("死信消息来自 exchange：" + fromExchange);
                System.out.println("死信消息来自 queue：" + fromQueue);
                System.out.println("死信消息产生的原因：" + reason);
            }
        };
        channel.basicConsume(Producer.DEAD_QUEUE, true, consumer);
    }
}
