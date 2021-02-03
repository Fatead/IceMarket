package com.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dto.RegisterDTO;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vicente
 * @since 2021-02-02
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User register(RegisterDTO registerDTO) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        log.info("UserServiceImpl#register:[{}]", JSON.toJSONString(registerDTO));
        User user = userMapper.selectOne(userQueryWrapper.eq("user_name",registerDTO.getUserName()));
        if(user != null){
            log.warn("用户已存在");
            return null;
        }else {
            log.info("注册成功");
            User saveUser = User.builder().
                    userName(registerDTO.getUserName()).
                    userType(2).
                    age(registerDTO.getAge()).
                    password(registerDTO.getPassword()).
                    address(registerDTO.getAddress()).
                    mail(registerDTO.getEmail()).build();
            userMapper.insert(saveUser);
            return saveUser;
        }
    }

}
