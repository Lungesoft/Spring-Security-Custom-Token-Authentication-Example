package com.lungesoft.token.example.controller;

import com.lungesoft.token.example.exception.UserAlreadyExistException;
import com.lungesoft.token.example.exception.WrongCredentialsException;
import com.lungesoft.token.example.model.SignRequest;
import com.lungesoft.token.example.model.SignResponse;
import com.lungesoft.token.example.service.UserService;
import com.lungesoft.token.example.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class CredentialsController {

    @Autowired
    private UserService userService;

    @PostMapping("/registration")
    public SignResponse handleRegistration(@RequestBody SignRequest requestBody) throws UserAlreadyExistException {
        return userService.userRegistration(requestBody);
    }

    @PostMapping("/login")
    public SignResponse handleLogin(@RequestBody SignRequest requestBody) throws WrongCredentialsException {
        return userService.userLogin(requestBody);
    }

    @GetMapping("/me")
    public SignResponse handleMe(Principal principal) {
        return userService.retrieveUserInfo(SecurityUtil.extractUserId(principal));
    }

}
