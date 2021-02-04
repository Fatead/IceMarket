package com.example.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.dto.user.UserRegisterReqDTO;
import com.example.dto.user.UserUpdateReqDTO;
import com.example.entity.User;
import com.example.infrastructure.convert.UserConvert;
import com.example.mapper.UserMapper;
import com.example.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
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
    public User register(UserRegisterReqDTO userRegisterReqDTO) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        log.info("UserServiceImpl#register:[{}]", JSON.toJSONString(userRegisterReqDTO));
        User user = userMapper.selectOne(userQueryWrapper.eq("user_name", userRegisterReqDTO.getUserName()));
        if(user != null){
            log.warn("用户已存在");
            return null;
        }else {
            log.info("注册成功");
            User saveUser = UserConvert.INSTANCE.convert(userRegisterReqDTO);
            userMapper.insert(saveUser);
            return saveUser;
        }
    }

    /**
     * 使用@Cacheable开启Redis缓存
     * @param userName
     * @return
     */
    @Cacheable(value = "my-redis-cache2",key = "#userName")
    public User getUserByName(String userName){
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        return userMapper.selectOne(userQueryWrapper.eq("user_name",userName));
    }

    @Override
    public boolean updateUserInfo(UserUpdateReqDTO updateReqDTO) {
        User user = User.builder().userName(updateReqDTO.getUserName()).build();
        if(user!=null){
            UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("user_name",updateReqDTO.getUserName());
            updateWrapper.set("mail",updateReqDTO.getMail());
            updateWrapper.set("password",updateReqDTO.getPassword());
            updateWrapper.set("nick_name",updateReqDTO.getNickName());
            userMapper.update(user,updateWrapper);
            return true;
        }else {
            log.error("UserServiceImpl#alterUserInfo user[{}] not existed",JSON.toJSONString(updateReqDTO));
            return false;
        }
    }

}
