package com.example.dto.user;

import lombok.Data;

/**
 * 用户登录的Request DTO
 */
@Data
public class UserLoginReqDTO {

    private String userName;
    private String password;

}
