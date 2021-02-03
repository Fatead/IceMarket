package com.example.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterDTO {

    private String userName;
    private String password;
    private String email;
    private String nickName;
    private int age;
    private String phoneNumber;
    private String gender;
    private String address;

}
