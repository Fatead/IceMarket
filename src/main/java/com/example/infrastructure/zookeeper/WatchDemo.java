package com.example.infrastructure.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * 监听单节点的内容
 */
public class WatchDemo {


    static List<String> children = null;

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        ZooKeeper zooKeeper = new ZooKeeper("127.0.0.1:2181", 3000, new Watcher() {

            //监听回调
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("正在监听中...");
            }

        });

        children = zooKeeper.getChildren("/", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("监听路径为：" + watchedEvent.getPath());
                System.out.println("监听的类型为：" + watchedEvent.getType());
                System.out.println("监听到被修改了");
                children.forEach(System.out::println);
            }
        });
        Thread.sleep(Long.MAX_VALUE);
    }

}
