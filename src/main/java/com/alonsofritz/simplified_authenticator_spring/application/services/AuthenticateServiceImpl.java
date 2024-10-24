package com.alonsofritz.simplified_authenticator_spring.application.services;

import org.springframework.stereotype.Service;

import com.alonsofritz.simplified_authenticator_spring.domain.services.AuthenticateService;

@Service("authenticateServiceImpl")
public class AuthenticateServiceImpl implements AuthenticateService {

    @Override
    public String execute(String username, String password) {
        System.out.println(username + " " + password);
        if (username.equals("alonso") && password.equals("1234")) {
            return "token";
        }
        return null;
    }
    
}
