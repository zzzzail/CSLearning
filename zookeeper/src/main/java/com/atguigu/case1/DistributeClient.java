package com.atguigu.case1;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 这是一个服务器动态上下线的示例
 */
public class DistributeClient {
    
    private String connectString = ":12181,:22181,:32181";
    private int sessionTimeout = 2000;
    private ZooKeeper zk;
    
    // 可用的 Server
    private List<String> availableServerList;
    
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeClient client = new DistributeClient();
        // 1 获取 zk 连接
        client.getConnect();
        // 2 监听 /servers 下面子节点的增加和删除
        client.updateServerList();
        // 3 业务逻辑（睡觉）
        client.business();
        // 在业务逻辑中可以使用 client.getAvailableServerList() 每次都获取最新的服务器地址
    }
    
    public DistributeClient() {
        this.availableServerList = new ArrayList<>();
    }
    
    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }
    
    private void updateServerList() throws KeeperException, InterruptedException {
        List<String> children = zk.getChildren("/servers", true);
        if (children == null) {
            this.availableServerList = new ArrayList<>();
            return;
        }
        else {
            for (String child : children) {
                byte[] data = zk.getData("/servers/" + child, false, null);
                this.availableServerList.add(new String(data));
            }
        }
    }
    
    private void getConnect() throws IOException {
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                try {
                    updateServerList();
                }
                catch (KeeperException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
    public List<String> getAvailableServerList() {
        return availableServerList;
    }
}
