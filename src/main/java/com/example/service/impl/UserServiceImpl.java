package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.dto.RegisterDTO;
import com.example.entity.User;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User register(RegisterDTO registerDTO) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        User user = userMapper.selectOne(userQueryWrapper.eq("user_name",registerDTO.getUserName()));
        if(user == null){
            return null;
        }else {
            User saveUser = User.builder().
                    userName(registerDTO.getUserName()).
                    userType(2).
                    age(registerDTO.getAge()).
                    address(registerDTO.getAddress()).
                    mail(registerDTO.getEmail()).build();
            userMapper.insert(user);
            return saveUser;
        }
    }

}
