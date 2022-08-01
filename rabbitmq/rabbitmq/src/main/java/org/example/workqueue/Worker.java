package org.example.workqueue;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.example.util.RabbitMQUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zail
 * @date 2022/6/28
 */
public class Worker {
    
    private static final String QUEUE_NAME = "hello";
    
    public static void main(String[] args) throws IOException {
        Channel channel = RabbitMQUtils.getChannel();
        
        // 收到消息后的回调
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            System.out.println("执行回调的线程：" + Thread.currentThread().getName());
            String receivedMessage = new String(delivery.getBody());
            System.out.println("接收到消息:" + receivedMessage);
            
            // 消息应答（处理完消息后的消息应答）
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        };
        
        // 消费者将消息取消了的回调
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
        // 回调方法是一个线程池去执行的
        String consumer = channel.basicConsume(QUEUE_NAME, false, deliverCallback, cancelCallback);
        System.out.println(consumer);
        
        // main 线程为什么在阻塞？
    }
}
