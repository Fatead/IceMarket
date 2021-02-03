package com.example.infrastructure.exception;

public class UserNameRegisteredException extends RuntimeException{

    public UserNameRegisteredException(String userName) {
        super("UserName: " + userName + " has been registered");
    }

}
