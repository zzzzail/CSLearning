package org.example.pubsub;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import org.example.util.RabbitMQUtils;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

/**
 * @author zail
 * @date 2022/6/28
 */
public class Publisher {
    
    private static final String QUEUE_NAME = "hello";
    
    public static void main(String[] args) {
        try (Channel channel = RabbitMQUtils.getChannel()) {
            channel.exchangeDeclare("exchange-01", BuiltinExchangeType.HEADERS);
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            // channel.queuePurge()
            // 从控制台当中接受信息
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNext()) {
                String message = scanner.next();
                channel.basicPublish("exchange-01", QUEUE_NAME, null, message.getBytes());
                System.out.println("发送消息完成:" + message);
            }
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
        catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
    
}
