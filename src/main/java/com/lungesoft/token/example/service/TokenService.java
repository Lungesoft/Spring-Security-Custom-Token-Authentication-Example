package com.lungesoft.token.example.service;

import com.lungesoft.token.example.exception.InvalidTokenException;
import com.lungesoft.token.example.model.BaseUser;

public interface TokenService {

    BaseUser retrieveBaseUser(String jwtToken) throws InvalidTokenException;

    String generateToken(BaseUser baseUser);

}
