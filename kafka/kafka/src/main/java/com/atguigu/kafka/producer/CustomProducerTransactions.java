package com.atguigu.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class CustomProducerTransactions {
    
    public static void main(String[] args) {
        
        // 0 配置
        Properties properties = new Properties();
        
        // 连接集群 bootstrap.servers
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        
        // 指定对应的key和value的序列化类型 key.serializer
        // properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,"org.apache.kafka.common.serialization.StringSerializer");
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        
        // 开启幂等性
        properties.put(ProducerConfig.ENABLE_IDEMPOTENCE_CONFIG, true);
        // 指定事务 id
        properties.put(ProducerConfig.TRANSACTIONAL_ID_CONFIG, "transactional_id_01");
        
        // 1 创建kafka生产者对象
        // "" hello
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<>(properties);
        // 初始化事务
        kafkaProducer.initTransactions();
        // 开启事务
        kafkaProducer.beginTransaction();
        try {
            // 2 发送数据
            for (int i = 0; i < 5; i++) {
                kafkaProducer.send(new ProducerRecord<>("first", "atguigu" + i));
            }
            
            // 报错
            int i = 1 / 0;
            
            // 提交事务
            kafkaProducer.commitTransaction();
        }
        catch (Exception e) {
            // 回滚事务 abort：n. 终止计划；流产，夭折；
            kafkaProducer.abortTransaction();
        }
        finally {
            // 3 关闭资源
            kafkaProducer.close();
        }
    }
}
