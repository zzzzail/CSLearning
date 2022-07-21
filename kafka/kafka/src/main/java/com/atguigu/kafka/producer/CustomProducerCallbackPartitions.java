package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.*;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class CustomProducerCallbackPartitions {
    
    public static void main(String[] args) throws InterruptedException {
        
        // 0 配置
        Properties properties = new Properties();
        
        // 连接集群 bootstrap.servers
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        
        // 指定对应的key和value的序列化类型 key.serializer
        // properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        
        /*
         关联自定义分区器
         
         Kafka 默认分区器 DefaultPartitioner
         */
        properties.put(ProducerConfig.PARTITIONER_CLASS_CONFIG, MyPartitioner.class.getName());
        
        // 1 创建kafka生产者对象
        // "" hello
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        
        // 2 发送数据
        for (int i = 0; i < 5; i++) {
            // 设置发往 0 号分区
            kafkaProducer.send(new ProducerRecord<>("first", 0, "", "hello" + i), new Callback() {
                @Override
                public void onCompletion(RecordMetadata metadata, Exception exception) {
                    if (exception == null) {
                        System.out.println("主题： " + metadata.topic() + " 分区： " + metadata.partition());
                    }
                    else {
                        exception.printStackTrace();
                    }
                }
            });
            
            Thread.sleep(2);
        }
        
        // 3 关闭资源
        kafkaProducer.close();
    }
}
