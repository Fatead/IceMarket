package com.example.dto.user;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserUpdateReqDTO {

    private String userName;
    private String password;
    private String mail;
    private String nickName;
}
