package org.example.simple;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author zail
 * @date 2022/6/28
 */
public class Consumer {
    
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
            System.out.println("等待接收消息......... ");
            DeliverCallback deliverCallback = new DeliverCallback() {
                @Override
                public void handle(String consumerTag, Delivery message) throws IOException {
                    String msg = new String(message.getBody());
                    System.out.println("msg = " + msg);
                }
            };
            CancelCallback cancelCallback = new CancelCallback() {
                @Override
                public void handle(String consumerTag) throws IOException {
                    System.out.println("消息消费被中断");
                }
            };
            // 消费消息
            channel.basicConsume(Producer.QUEUE_NAME, true, deliverCallback, cancelCallback);
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
