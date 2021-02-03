package com.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Role;
import com.example.entity.User;
import com.example.mapper.RoleMapper;
import com.example.mapper.UserMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleMapper roleMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if (s == null || "".equals(s))
        {
            throw new RuntimeException("用户不能为空");
        }
        // 调用方法查询用户
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("user_name",s);
        User user = userMapper.selectOne(userQueryWrapper);
        if (user == null)
        {
            throw new RuntimeException("用户不存在");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        QueryWrapper<Role> roleQueryWrapper = new QueryWrapper<>();
        roleQueryWrapper.eq("user_name",s);
        for (Role role: roleMapper.selectList(roleQueryWrapper))
        {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(),"{noop}"+user.getPassword(),authorities);
    }

}
