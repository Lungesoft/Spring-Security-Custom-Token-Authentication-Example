package com.lungesoft.token.example.exception;

public class UserAlreadyExistException extends Exception {
    @Override
    public String getMessage() {
        return "User witch such nickname already exists";
    }
}
