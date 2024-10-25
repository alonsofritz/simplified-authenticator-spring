package com.alonsofritz.simplified_authenticator_spring.domain.factory;

import com.alonsofritz.simplified_authenticator_spring.domain.entities.Token;

public class TokenFactory {
    public static Token create(String accessToken, String refreshToken) {
        if (accessToken == null) {
            throw new IllegalArgumentException("Access token must not be null");
        }
        return new Token(accessToken, refreshToken);
    }
}
