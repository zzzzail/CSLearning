package com.example.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.PropertyKeyConst;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * @author zail
 * @date 2022/8/1
 */
public class SimpleDemo {
    
    public static void main(String[] args) throws NacosException, InterruptedException {
        String serverAddr = "127.0.0.1:8848";
        String namespace = "dev";          // 命名空间
        String group = "DEFAULT_GROUP";    // 分组
        String dataId = "my-app";          // 配置文件名
        Properties properties = new Properties();
        properties.put(PropertyKeyConst.SERVER_ADDR, serverAddr);
        properties.put(PropertyKeyConst.NAMESPACE, namespace);
        
        ConfigService configService = NacosFactory.createConfigService(properties);
        String content = configService.getConfig(dataId, group, 5000);
        System.out.println(content);
        
        // 添加监听器，监听配置的改变
        configService.addListener(dataId, group, new Listener() {
            @Override
            public Executor getExecutor() {
                return null;
            }
            
            @Override
            public void receiveConfigInfo(String configInfo) {
                System.out.println("收到新的配置信息：");
                System.out.println(configInfo);
            }
        });
        
        Thread.sleep(Long.MAX_VALUE);
        
        // Thread.sleep(3000);
        // 发布新的配置
        // boolean isPublishOk = configService.publishConfig(dataId, group, "content");
        // System.out.println(isPublishOk);
        
        // 删除配置
        // boolean isRemoveOk = configService.removeConfig(dataId, group);
        // System.out.println(isRemoveOk);
        // Thread.sleep(3000);
        
        // 获取配置信息
        // content = configService.getConfig(dataId, group, 5000);
        // System.out.println(content);
        // Thread.sleep(300000);
    }
}
