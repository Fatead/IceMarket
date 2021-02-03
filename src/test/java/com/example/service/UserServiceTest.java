package com.example.service;

import com.example.DemoApplication;
import com.example.dto.RegisterDTO;
import com.example.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
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


    @Test
    public void registerTest(){
        RegisterDTO registerDTO = RegisterDTO.builder().userName("324123").
                nickName("Tom")
                .build();
        userService.register(registerDTO);
    }

}
