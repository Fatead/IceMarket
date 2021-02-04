package com.example.dto.user;

import lombok.Builder;
import lombok.Data;

/**
 * 用户注册的Request DTO
 */
@Data
@Builder
public class UserRegisterReqDTO {

    private String userName;
    private String password;
    private String mail;
    private String nickName;

}
