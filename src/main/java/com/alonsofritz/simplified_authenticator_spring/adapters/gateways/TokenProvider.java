package com.alonsofritz.simplified_authenticator_spring.adapters.gateways;

import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;

public interface TokenProvider {
    String generateToken(User user);
    String getEmailFromToken(String token);
}
