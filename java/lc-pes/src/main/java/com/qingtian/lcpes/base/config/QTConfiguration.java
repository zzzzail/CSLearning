package com.qingtian.lcpes.base.config;

import com.ibm.mq.jms.MQConnectionFactory;
import com.ibm.msg.client.wmq.WMQConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.JmsTransactionManager;
import org.springframework.jms.core.JmsOperations;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhangxq
 * @since 2022/9/5
 */
@Configuration
@Slf4j
public class QTConfiguration {
    @Value("${qingtian.mq.ibm.host}")
    private String host;
    @Value("${qingtian.mq.ibm.port}")
    private Integer port;
    @Value("${qingtian.mq.ibm.manager}")
    private String queueManager;
    @Value("${qingtian.mq.ibm.channel}")
    private String channel;
    @Value("${qingtian.mq.ibm.username}")
    private String username;
    @Value("${qingtian.mq.ibm.password}")
    private String password;
    @Value("${qingtian.mq.ibm.receive-timeout}")
    private long receiveTimeout;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    /**
     * 配置连接工厂:
     * CCSID要与连接到的队列管理器一致，Windows下默认为1381，
     * Linux下默认为1208。1208表示UTF-8字符集，建议把队列管理器的CCSID改为1208
     *
     * @return
     */
    @Bean
    public MQConnectionFactory mqConnectionFactory() {
        log.info("=====ibm-info===host===" + host);
        log.info("=====ibm-info===port===" + port);
        log.info("=====ibm-info===channel===" + channel);
        log.info("=====ibm-info===queueManager===" + queueManager);
        MQConnectionFactory mqConnectionFactory = new MQConnectionFactory();
        mqConnectionFactory.setHostName(host);
        try {
            mqConnectionFactory.setTransportType(WMQConstants.WMQ_CM_CLIENT);
            mqConnectionFactory.setCCSID(1208);
            mqConnectionFactory.setChannel(channel);
            mqConnectionFactory.setPort(port);
            mqConnectionFactory.setQueueManager(queueManager);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mqConnectionFactory;
    }

    /**
     * 配置缓存连接工厂:
     * 不配置该类则每次与MQ交互都需要重新创建连接，大幅降低速度。
     */
    @Bean
    @Primary
    public CachingConnectionFactory cachingConnectionFactory() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setTargetConnectionFactory(mqConnectionFactory());
        cachingConnectionFactory.setSessionCacheSize(500);
        cachingConnectionFactory.setReconnectOnException(true);
        return cachingConnectionFactory;
    }

    /**
     * 配置事务管理器:
     * 不使用事务可以跳过该步骤。
     * 如需使用事务，可添加注解@EnableTransactionManagement到程序入口类中，事务的具体用法可参考Spring Trasaction。
     *
     * @param cachingConnectionFactory
     * @return
     */
    @Bean
    public PlatformTransactionManager jmsTransactionManager(CachingConnectionFactory cachingConnectionFactory) {
        JmsTransactionManager jmsTransactionManager = new JmsTransactionManager();
        jmsTransactionManager.setConnectionFactory(cachingConnectionFactory);
        return jmsTransactionManager;
    }

    /**
     * 配置JMS模板:
     * JmsOperations为JmsTemplate的实现接口。
     * 重要：不设置setReceiveTimeout时，当队列为空，从队列中取出消息的方法将会一直挂起直到队列内有消息
     *
     * @param cachingConnectionFactory
     * @return
     */
    @Bean
    public JmsOperations jmsOperations(CachingConnectionFactory cachingConnectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(cachingConnectionFactory);
        jmsTemplate.setReceiveTimeout(receiveTimeout);
        return jmsTemplate;
    }
}
