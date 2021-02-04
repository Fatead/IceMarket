package com.example.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.example.dto.user.UserRegisterReqDTO;
import com.example.dto.user.UserUpdateReqDTO;
import com.example.entity.User;
import com.example.infrastructure.exception.UserNameRegisteredException;
import com.example.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author vicente
 * @since 2021-02-02
 */
@Api(tags = "用户信息管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody UserRegisterReqDTO userRegisterReqDTO){
        User user = userService.register(userRegisterReqDTO);
        if(user!=null){
            return ResponseEntity.ok(user);
        }else {
            throw new UserNameRegisteredException(userRegisterReqDTO.getUserName());
        }
    }

    @ApiOperation("用户信息修改")
    @PostMapping("/alter")
    public ResponseEntity<String> alterUserInfo(@RequestBody UserUpdateReqDTO userUpdateReqDTO){
        boolean result = userService.updateUserInfo(userUpdateReqDTO);
        if(result){
            return ResponseEntity.ok("更新成功");
        }else {
            return ResponseEntity.ok("更新失败");
        }
    }

}