package com.example.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.DemoApplication;
import com.example.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class UserMapperTest {


    @Resource
    private UserMapper userMapper;

    @Test
    public void addUserTest(){
        User user = User.builder().userName("李四").age(20).mail("123@qq.com").password("123").address("somewhere").gender("male").phoneNumber("1231").build();
        userMapper.insert(user);
    }

    @Test
    public void getAllUserTest(){
        List<User> users = userMapper.selectList(null);
        for(User user:users){
            System.out.println("用户名为:" + user.getUserName());
        }
        users.forEach(System.out::println);
    }


    @Test
    public void selectByConditionTest(){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.ge("user_name","李四");
        List<User> userList = userMapper.selectList(userQueryWrapper);
        userList.forEach(System.out::println);
    }

}