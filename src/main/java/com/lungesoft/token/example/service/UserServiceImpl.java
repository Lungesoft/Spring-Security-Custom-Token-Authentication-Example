package com.lungesoft.token.example.service;

import com.lungesoft.token.example.exception.UserAlreadyExistException;
import com.lungesoft.token.example.exception.WrongCredentialsException;
import com.lungesoft.token.example.model.BaseUser;
import com.lungesoft.token.example.model.SignRequest;
import com.lungesoft.token.example.model.SignResponse;
import com.lungesoft.token.example.model.User;
import com.lungesoft.token.example.util.SecurityUtil;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserServiceImpl implements UserService {

    private AtomicLong idIncrement = new AtomicLong();
    private List<User> userStorage = new CopyOnWriteArrayList<>();

    private TokenService tokenService;

    public UserServiceImpl(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public SignResponse userRegistration(SignRequest requestBody) throws UserAlreadyExistException {
        for (User user : userStorage) {
            if (user.getLogin().equals(requestBody.getLogin())) {
                throw new UserAlreadyExistException();
            }
        }
        User user = new User();
        user.setId(idIncrement.incrementAndGet());
        user.setLogin(requestBody.getLogin());
        user.setSha256Password(SecurityUtil.sha256Crypt(requestBody.getPassword()));
        userStorage.add(user);
        return new SignResponse(
                user.getId(),
                user.getLogin(),
                tokenService.generateToken(new BaseUser(user.getId()))
        );
    }

    @Override
    public SignResponse userLogin(SignRequest requestBody) throws WrongCredentialsException {
        User user = null;
        for (User currentUser : userStorage) {
            if (currentUser.getLogin().equals(requestBody.getLogin())) {
                if (currentUser.getSha256Password().equals(SecurityUtil.sha256Crypt(requestBody.getPassword()))) {
                    user = currentUser;
                } else {
                    break;
                }
            }
        }
        if (user == null) {
            throw new WrongCredentialsException();
        }
        return new SignResponse(
                user.getId(),
                user.getLogin(),
                tokenService.generateToken(new BaseUser(user.getId()))
        );
    }

    @Override
    public SignResponse retrieveUserInfo(Long id) {
        for (User currentUser : userStorage) {
            if (currentUser.getId().equals(id)) {
                return new SignResponse(
                        currentUser.getId(),
                        currentUser.getLogin(),
                        tokenService.generateToken(new BaseUser(currentUser.getId()))
                );
            }
        }
        throw new IllegalStateException("invalid user storage");
    }


}
