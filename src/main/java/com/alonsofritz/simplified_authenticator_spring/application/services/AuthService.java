package com.alonsofritz.simplified_authenticator_spring.application.services;

import com.alonsofritz.simplified_authenticator_spring.application.usecases.token.GenerateToken;
import com.alonsofritz.simplified_authenticator_spring.application.usecases.user.AuthenticateUser;
import com.alonsofritz.simplified_authenticator_spring.domain.entities.Token;
import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("authService")
public class AuthService {

    private final AuthenticateUser authenticateUser;

    private final GenerateToken generateToken;

    public AuthService(
            @Qualifier("authenticateUser") AuthenticateUser authenticateUser,
            @Qualifier("generateToken") GenerateToken generateToken
    ) {
        this.authenticateUser = authenticateUser;
        this.generateToken = generateToken;
    }

    public Token login(String email, String password) {
        try {
            User authenticatedUser = authenticateUser.execute(email, password);
            return generateToken.execute(authenticatedUser);
        } catch (RuntimeException e) {
            throw new RuntimeException("Invalid email or password");
        }
    }
}
