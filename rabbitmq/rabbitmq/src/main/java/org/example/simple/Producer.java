package org.example.simple;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Enumeration;
import java.util.concurrent.TimeoutException;

/**
 * @author zail
 * @date 2022/6/28
 */
public class Producer {
    
    public static final String QUEUE_NAME = "hello";
    
    public static void main(String[] args) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        factory.setUsername("guest");
        factory.setPassword("guest");
        try (
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel()
        ) {
            channel.queueDeclare(
                    QUEUE_NAME,  // 队列名称
                    false,       // 是否进行持久化
                    false,       // 是否仅有一个消费者消费，true 仅有一个消费者消费，false 有多个消费者消费
                    false,       // 是否自动删除
                    null         // 其他的一些配置参数
            );
            
            // 发送消息
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("发送完毕！");
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
