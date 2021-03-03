package com.example.infrastructure.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * 在分布式系统当中，主节点可以有多台，可以进行动态上下线，
 * 当有任何一台机器发生了动态的上下线，任何一台客户端都能感知到
 *
 * 1.创建客户端和服务器端
 * 2.启动client端进行监听
 * 3.启动server端进行注册
 * 4.当server端发生上下线时，client端都能感知到
 *
 */
public class ZkServer {

    private String connectString = "127.0.0.1:2181";

    private int sessionTimeout = 3000;

    ZooKeeper zooKeeper = null;
    //定义父节点
    private String parentNode = "/servers";

    //连接zkServer
    public void getConnect() throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {

            }
        });
    }

    //注册信息
    public void register(String hostName) throws KeeperException, InterruptedException {
        try {
            String node = zooKeeper.create(parentNode + "/server",hostName.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE,
                    CreateMode.EPHEMERAL_SEQUENTIAL);
            System.out.println(node);
        }catch (Exception e){

        }

    }

    //构造服务器
    public void build() throws InterruptedException {
        System.out.println("服务器上线了");
        Thread.sleep(Long.MAX_VALUE);
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZkServer zkServer = new ZkServer();
        zkServer.getConnect();
        zkServer.register("tomcat");
        zkServer.build();
    }

}
