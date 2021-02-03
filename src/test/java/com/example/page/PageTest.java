package com.example.page;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.DemoApplication;
import com.example.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class PageTest {

    @Test
    public void testPage(){
        Page<User> page = new Page<>();

    }


}
