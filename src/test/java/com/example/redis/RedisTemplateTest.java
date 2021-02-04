package com.example.redis;

import com.example.DemoApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    public void RedisOpsTest(){
        redisTemplate.opsForValue().set("Spring-data","redis");
        System.out.println(redisTemplate.opsForValue().get("Spring-data"));
    }

}
