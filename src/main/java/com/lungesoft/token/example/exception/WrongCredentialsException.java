package com.lungesoft.token.example.exception;

public class WrongCredentialsException extends Exception {

    @Override
    public String getMessage() {
        return "Wrong credentials";
    }
}
