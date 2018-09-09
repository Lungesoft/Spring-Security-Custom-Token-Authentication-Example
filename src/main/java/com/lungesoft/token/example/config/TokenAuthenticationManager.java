package com.lungesoft.token.example.config;

import com.lungesoft.token.example.exception.InvalidTokenException;
import com.lungesoft.token.example.model.BaseUser;
import com.lungesoft.token.example.service.TokenService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TokenAuthenticationManager implements AuthenticationManager {

    private TokenService tokenService;

    public TokenAuthenticationManager(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) {
        if (authentication instanceof TokenAuthentication) {
            return processAuthentication((TokenAuthentication) authentication);
        } else {
            authentication.setAuthenticated(false);
            return authentication;
        }
    }

    private TokenAuthentication processAuthentication(TokenAuthentication authentication) {
        String token = authentication.getToken();
        try {
            BaseUser baseUser = tokenService.retrieveBaseUser(token);
            return buildFullTokenAuthentication(authentication, baseUser);
        } catch (InvalidTokenException e) {
            throw new AuthenticationServiceException("Invalid token", e);
        }
    }

    private TokenAuthentication buildFullTokenAuthentication(TokenAuthentication authentication, BaseUser baseUser) {
        authentication.setAuthenticated(true);
        authentication.setAuthorities(getAuthorities());
        authentication.setUserEntity(baseUser);
        return authentication;
    }

    private List<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> defaultAuthorities = new ArrayList<>();
        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority("USER");
        defaultAuthorities.add(simpleGrantedAuthority);
        return defaultAuthorities;
    }
}
