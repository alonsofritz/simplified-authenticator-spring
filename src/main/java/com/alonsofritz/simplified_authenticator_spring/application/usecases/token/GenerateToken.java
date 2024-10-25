package com.alonsofritz.simplified_authenticator_spring.application.usecases.token;

import com.alonsofritz.simplified_authenticator_spring.adapters.gateways.TokenProvider;
import com.alonsofritz.simplified_authenticator_spring.domain.entities.Token;
import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;
import com.alonsofritz.simplified_authenticator_spring.domain.factory.TokenFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("generateToken")
public class GenerateToken {

    private final TokenProvider tokenProvider;

    public GenerateToken(
            @Qualifier("jwtTokenProvider") TokenProvider tokenProvider
    ) {
        this.tokenProvider = tokenProvider;
    }

    public Token execute(User user) {
        String accesToken = tokenProvider.generateToken(user);
        Token token = TokenFactory.create(accesToken, null);
        if (token.getAccessToken() == null || token.getAccessToken().isEmpty()) {
            throw new RuntimeException("Error generating token");
        }
        return token;
    }
}
