package com.alonsofritz.simplified_authenticator_spring.domain.services;

import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;

public interface SignUpService {
    User execute(String email, String password, String permission);
}
