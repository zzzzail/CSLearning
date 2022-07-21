package com.atguigu.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class ZKClient {
    
    // 注意：逗号左右不能有空格
    private String connectString = ":12181,:22181,:32181";
    private int sessionTimeout = 2000;
    private ZooKeeper zookeeperClient;
    
    @Before
    public void init() throws IOException {
        zookeeperClient = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("-------------------------------");
                List<String> children = null;
                try {
                    children = zookeeperClient.getChildren("/", true);
                    
                    for (String child : children) {
                        System.out.println(child);
                    }
                    
                    System.out.println("-------------------------------");
                }
                catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    @Test
    public void create() throws KeeperException, InterruptedException {
        Stat stat = zookeeperClient.exists("/atguigu", false);
        if (stat == null) {
            String nodeCreated = zookeeperClient.create(
                    "/atguigu",
                    "ss.avi".getBytes(),
                    ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.PERSISTENT);
        }
    }
    
    @Test
    public void getChildren() throws KeeperException, InterruptedException {
        List<String> children = zookeeperClient.getChildren("/", true);
        for (String child : children) {
            System.out.println(child);
        }
    }
    
    @Test
    public void exist() throws KeeperException, InterruptedException {
        Stat stat = zookeeperClient.exists("/atguigu", false);
        System.out.println(stat == null ? "not exist " : "exist");
    }
}
