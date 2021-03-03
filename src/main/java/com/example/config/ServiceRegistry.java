package com.example.config;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于Zookeeper的服务注册
 * @author 程就人生
 * @date 2019年10月30日
 */
public class ServiceRegistry {

    private static Logger log = LoggerFactory.getLogger(ServiceRegistry.class);

    private CountDownLatch latch = new CountDownLatch(1);
    //这个可以放到配置文件里，对应Zookeeper已经启动的ip+port
    private String registryAddress = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";

    private String nodePath = "/app";

    private String cnodePath = "/chatting";

    private int timeout = 3000;

    public ServiceRegistry() {
    }

    public void register(String data) {
        if (data != null) {
            ZooKeeper zk = connectServer();
            if (zk != null) {
                createNode(zk, data);
            }
        }
    }

    /**
     * 连接 zookeeper 服务器
     * @return
     */
    private ZooKeeper connectServer() {
        ZooKeeper zk = null;
        try {
            zk = new ZooKeeper(registryAddress, timeout, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getState() == Event.KeeperState.SyncConnected) {
                        latch.countDown();
                        log.info("Watcher.........");
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
     * 创建节点
     * @param zk
     * @param data
     */
    private void createNode(ZooKeeper zk, String data) {
        try {
            //父节点不存在时进行创建
            Stat stat = zk.exists(nodePath, true);
            if(stat == null){
                zk.create(nodePath, null,  ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }
            //这里的第一个参数和3.4.13版本的zookeeper不一样，如果不加父目录，直接就是使用/app/会报错，所以智能加父目录
            //CreateMode.EPHEMERAL_SEQUENTIAL,创建临时顺序节点,客户端会话结束后，节点将会被删除
            String createPath = zk.create(nodePath+cnodePath, data.getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            log.info("create zookeeper node ({} =&gt; {} =&gt; {})", data, createPath);
        } catch (KeeperException | InterruptedException e) {
            log.info("", e);
            e.printStackTrace();
        }
    }
}
