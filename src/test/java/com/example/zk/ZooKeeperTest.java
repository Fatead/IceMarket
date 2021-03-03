package com.example.zk;


import com.example.DemoApplication;
import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class ZooKeeperTest {

    private String connectString = "127.0.0.1:2181";

    private int sessionTimeout = 3000;
    ZooKeeper zooKeeper = null;


    //初始化客户端
    @Before
    public void init() throws IOException {
        zooKeeper = new ZooKeeper(connectString, sessionTimeout, new Watcher() {
            // 回调监听
            @Override
            public void process(WatchedEvent event) {
                // System.out.println(event.getPath() + "\t" + event.getState() + "\t" + event.getType());
                try {
                    List<String> children = zooKeeper.getChildren("/", true);
                    for (String c : children) {
                        // System.out.println(c);
                    }
                } catch (KeeperException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    //创建子节点
    @Test
    public void createZnode() throws KeeperException, InterruptedException {
        String path = zooKeeper.create("/servers", "world".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println(path);
    }


    //获取子节点，获取zookeeper中的子节点并输出
    @Test
    public void getChildZNodeTest() throws KeeperException, InterruptedException {
        List<String> children = zooKeeper.getChildren("/",true);
        children.forEach(System.out::println);
    }

    //删除zk中的ZNode
    @Test
    public void deleteChildZNodeTest() throws KeeperException, InterruptedException {
        zooKeeper.delete("/hello",-1);
    }

    //修改数据
    @Test
    public void testExist() throws KeeperException, InterruptedException {
        Stat exists = zooKeeper.exists("/hello",false);
        System.out.println(exists == null ? "not exists" : "exists");
    }

}
