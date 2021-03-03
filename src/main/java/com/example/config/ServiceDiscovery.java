package com.example.config;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于Zookeeper的服务发现
 * @author 程就人生
 * @date 2019年10月30日
 */
public class ServiceDiscovery {

    private static Logger log = LoggerFactory.getLogger(ServiceDiscovery.class);

    private CountDownLatch latch = new CountDownLatch(1);

    private volatile List<String> serviceAddressList = new ArrayList<>();
    //这个可以放到配置文件里，对应Zookeeper已经启动的ip+port
    private String registryAddress = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";

    private String nodePath = "/app";

    private int timeout = 3000;
    //注册中心的地址
    public ServiceDiscovery() {
        ZooKeeper zk = connectServer();
        if (zk != null) {
            watchNode(zk);
        }
    }

    /**
     * 通过服务发现，获取服务提供方的地址
     * @return
     */
    public String discover() {
        String data = null;
        int size = serviceAddressList.size();
        if (size > 0) {
            if (size == 1) {
                //只有一个服务提供方
                data = serviceAddressList.get(0);
                log.info("unique service address :{}", data);
            } else {
                //使用随机分配法,简单的负载均衡法
                data = serviceAddressList.get(ThreadLocalRandom.current().nextInt(size));
                log.info("choose an address : {}",data);
            }
        }
        return data;
    }

    /**
     * 连接 zookeeper
     * @return
     */
    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(registryAddress, timeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Watcher.Event.KeeperState.SyncConnected) {
                        latch.countDown();
                    }
                }
            });
            latch.await();
        } catch (IOException | InterruptedException e) {
            log.error("", e);
            e.printStackTrace();
        }
        return zk;
    }

    /**
     * 获取服务地址列表
     * @param zk
     */
    private void watchNode(final ZooKeeper zk) {
        try {
            //获取子节点列表
            List<String> nodeList = zk.getChildren(nodePath,new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getType() == Event.EventType.NodeChildrenChanged) {
                        // 发生子节点变化时再次调用此方法更新服务地址
                        watchNode(zk);
                    }
                }
            });
            List<String> dataList = new ArrayList<>();
            for (String node :nodeList) {
                byte[] bytes = zk.getData(nodePath + "/" + node, false, null);
                dataList.add(new String(bytes));
            }
            log.info("node data: {}", dataList);
            this.serviceAddressList = dataList;
        }catch(KeeperException|InterruptedException e){
            log.error("", e);
            e.printStackTrace();
        }
    }

    public static void main(String[] agro){
        //服务发现
        ServiceDiscovery serviceDiscovery = new ServiceDiscovery();
        serviceDiscovery.discover();
    }
}