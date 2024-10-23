package com.alonsofritz.simplified_authenticator_spring.domain.services;

public interface AuthenticateService {
    String execute(String username, String password);
}
