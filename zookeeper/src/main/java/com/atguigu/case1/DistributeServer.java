package com.atguigu.case1;

import org.apache.zookeeper.*;

import java.io.IOException;

public class DistributeServer {
    
    private String connectString = ":12181,:22181,:32181";
    private int sessionTimeout = 2000;
    private ZooKeeper zk;
    
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        DistributeServer server = new DistributeServer();
        // 1 获取 ZK 连接
        server.getConnect();
        // 2 注册服务器到 ZK 集群
        server.regist(args[0], args[1]);
        // 3 启动业务逻辑（睡觉）
        server.business();
    }
    
    private void business() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }
    
    /**
     * 向 ZK 注册一个 Server
     *
     * @param hostname
     * @throws KeeperException
     * @throws InterruptedException
     */
    private void regist(String hostname, String ipAddress) throws KeeperException, InterruptedException {
        String create = zk.create(
                "/servers/" + hostname,
                ipAddress.getBytes(),
                ZooDefs.Ids.OPEN_ACL_UNSAFE,
                CreateMode.EPHEMERAL_SEQUENTIAL // 创建一个临时的带序号的结点
        );
        System.out.println(create); // Create 后的信息
        System.out.println(hostname + " is online");
    }
    
    private void getConnect() throws IOException {
        zk = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
            }
        });
    }
}
