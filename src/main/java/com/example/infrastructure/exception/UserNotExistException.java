package com.example.infrastructure.exception;

public class UserNotExistException extends RuntimeException{

    public UserNotExistException(String userName) {
        super("UserName: " + userName + "not exist");
    }

}
