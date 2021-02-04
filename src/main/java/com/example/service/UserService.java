package com.example.service;

import com.example.dto.RegisterDTO;
import com.example.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.cache.annotation.Cacheable;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author vicente
 * @since 2021-02-02
 */
public interface UserService extends IService<User> {


    public User register(RegisterDTO registerDTO);


    @Cacheable(value = "my-redis-cache2",key = "#userName")
    public User getUserByName(String userName);

}
