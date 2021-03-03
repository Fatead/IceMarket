package com.example.infrastructure.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ZkClient {

    private String connectString = "127.0.0.1:2181";

    private int sessionTimeout = 3000;

    ZooKeeper zooKeeper = null;

    /**
     * 业务逻辑
     */
    public void getWatch() throws InterruptedException {
        Thread.sleep(Long.MAX_VALUE);
    }

    /**
     * 监听服务的节点信息
     */
    public void getServers() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/servers",true);
        List<String> serverList = new ArrayList<>();
        for (String child : children) {
            byte[] data = zooKeeper.getData("/servers/" + child,true,null);
            serverList.add(new String(data));
        }
        //打印服务器列表
        System.out.println(serverList);
    }


    /**
     * 连接集群
     */
    public void getConnect() throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                List<String> children = null;
                try {
                    children = zooKeeper.getChildren("/servers",true);
                    // 创建集合存储服务器列表
                    ArrayList<String> serverList = new ArrayList<String>();

                    // 获取每个节点的数据
                    for (String c : children) {
                        byte[] data = zooKeeper.getData("/servers/" + c, true, null);
                        serverList.add(new String(data));
                    }

                    // 打印服务器列表
                    System.out.println(serverList);
                }catch (KeeperException | InterruptedException e){
                    e.printStackTrace();
                }
            }

        });
    }

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        // 1.获取连接
        ZkClient zkClient = new ZkClient();
        zkClient.getConnect();

        // 2.监听服务的节点信息
        zkClient.getServers();

        // 3.业务逻辑（一直监听）
        zkClient.getWatch();
    }

}
