package org.example.dlx;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BasicProperties;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import org.example.util.RabbitMQUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author zail
 * @date 2022/8/2
 */
public class Producer {
    
    public static final String NORMAL_EXCHANGE = "normal-exchange";
    public static final String NORMAL_QUEUE = "normal-queue";
    public static final String DEAD_EXCHANGE = "dead-exchange";
    public static final String DEAD_QUEUE = "dead-queue";
    
    public static void main(String[] argv) throws Exception {
        try (Channel channel = RabbitMQUtils.getChannel()) {
            // 正常队列绑定死信队列信息
            Map<String, Object> params = new HashMap<>();
            // 正常队列设置死信交换机 参数 key 是固定值
            params.put("x-dead-letter-exchange", DEAD_EXCHANGE);
            // 正常队列设置死信 routing-key 参数 key 是固定值
            params.put("x-dead-letter-routing-key", "rk1");
            
            channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(NORMAL_QUEUE, true, false, false, params);
            channel.queueBind(NORMAL_QUEUE, NORMAL_EXCHANGE, "rk1");
            
            channel.exchangeDeclare(DEAD_EXCHANGE, BuiltinExchangeType.DIRECT);
            channel.queueDeclare(DEAD_QUEUE, true, false, false, null);
            channel.queueBind(DEAD_QUEUE, DEAD_EXCHANGE, "rk1");
            
            // 设置消息的 TTL 时间
            AMQP.BasicProperties properties = new AMQP.BasicProperties().builder()
                    .expiration("1000") // 超时时间
                    .build();
            // 该信息是用作演示队列个数限制
            for (int i = 1; i < 11; i++) {
                String message = "info" + i;
                channel.basicPublish(NORMAL_EXCHANGE, "rk1", properties, message.getBytes());
                System.out.println("生产者发送消息:" + message);
            }
        }
        
    }
}
