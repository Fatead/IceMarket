package com.example.controller;


import com.example.dto.RegisterDTO;
import com.example.entity.User;
import com.example.infrastructure.exception.UserNameRegisteredException;
import com.example.service.UserService;
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
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody RegisterDTO registerDTO){
        User user = userService.register(registerDTO);
        if(user!=null){
            return ResponseEntity.ok(user);
        }else {
            throw new UserNameRegisteredException(registerDTO.getUserName());
        }
    }



}