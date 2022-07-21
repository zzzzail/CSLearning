package com.atguigu.kafka.consumer;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CustomConsumer {
    
    public static void main(String[] args) {
        
        // 0 配置
        Properties properties = new Properties();
        
        // 连接 bootstrap.servers
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        // 配置反序列化
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        // 配置消费者组id
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "test5");
        /*
            设置分区分配策略
            区间分配策略：RangeAssignor.class.getName();
            轮询分配策略：RoundRobinAssignor.class.getName();
            黏性分配策略：StickyAssignor.class.getName();
         */
        properties.put(ConsumerConfig.PARTITION_ASSIGNMENT_STRATEGY_CONFIG, "org.apache.kafka.clients.consumer.StickyAssignor");
        
        // 1 创建一个消费者  "", "hello"
        KafkaConsumer<String, String> kafkaConsumer = new KafkaConsumer<>(properties);
        
        // 2 订阅主题 first
        List<String> topics = new ArrayList<>();
        topics.add("first");
        kafkaConsumer.subscribe(topics);
        
        // 3 消费数据
        while (true) {
            ConsumerRecords<String, String> consumerRecords = kafkaConsumer.poll(Duration.ofSeconds(1));
            for (ConsumerRecord<String, String> consumerRecord : consumerRecords) {
                System.out.println(consumerRecord);
            }
            // 提交 offset
            kafkaConsumer.commitAsync();
        }
    }
}
