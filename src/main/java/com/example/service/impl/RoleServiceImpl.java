package com.example.service.impl;

import com.example.entity.Role;
import com.example.mapper.RoleMapper;
import com.example.service.RoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author vicente
 * @since 2021-02-03
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
