package com.example.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.User;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class UserServiceTest {


    @Resource
    private UserService userService;


    @Test
    public void saveUserBatchTest(){
        List<User> userList = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            User user =  User.builder().userName(i + "-Tom").build();
            userList.add(user);
        }
        userService.saveBatch(userList);
    }

}
