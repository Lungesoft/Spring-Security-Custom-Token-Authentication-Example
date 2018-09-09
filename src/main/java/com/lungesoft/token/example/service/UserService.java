package com.lungesoft.token.example.service;

import com.lungesoft.token.example.exception.UserAlreadyExistException;
import com.lungesoft.token.example.exception.WrongCredentialsException;
import com.lungesoft.token.example.model.SignRequest;
import com.lungesoft.token.example.model.SignResponse;

public interface UserService {

    SignResponse userRegistration(SignRequest requestBody) throws UserAlreadyExistException;

    SignResponse userLogin(SignRequest requestBody) throws WrongCredentialsException;

    SignResponse retrieveUserInfo(Long id);
}
