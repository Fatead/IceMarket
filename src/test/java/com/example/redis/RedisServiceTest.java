package com.example.redis;

import com.example.DemoApplication;
import com.example.infrastructure.redis.RedisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class RedisServiceTest {

    @Resource
    private RedisService redisService;

    @Test
    public void hashOpsTest(){
        redisService.hashPut("set-space","set","set");
    }

}
