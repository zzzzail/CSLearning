package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class CustomProducerParameters {
    
    public static void main(String[] args) {
        // 0. 生产者配置
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        
        // 设置缓冲区大小，默认 32M
        properties.put(ProducerConfig.BUFFER_MEMORY_CONFIG, 1 << (5 + 20));
        // 设置批次大小，默认 16k，设置为 32k
        properties.put(ProducerConfig.BATCH_SIZE_CONFIG, 1 << (5 + 10));
        // 设置等待时间，默认 0ms，设置为 100ms
        properties.put(ProducerConfig.LINGER_MS_CONFIG, 100);
        // 使用 snappy 压缩算法，默认不使用压缩算法，也可以使用 gzip、lz4、zstd 压缩算法
        properties.put(ProducerConfig.COMPRESSION_TYPE_CONFIG, "snappy");
        
        // 1. 创建生产者
        try (KafkaProducer<String, String> producer = new KafkaProducer<>(properties)) {
            
            // 2. 发送数据
            for (int i = 0; i < 5; i++) {
                producer.send(new ProducerRecord<>("first", "测试一些配置的消息" + i));
            }
        }
    }
}
