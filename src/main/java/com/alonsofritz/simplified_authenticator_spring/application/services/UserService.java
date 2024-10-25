package com.alonsofritz.simplified_authenticator_spring.application.services;

import com.alonsofritz.simplified_authenticator_spring.application.usecases.user.CreateUser;
import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserService {
    private final CreateUser createUser;

    public UserService(
            @Qualifier("createUser") CreateUser createUser
    ) {
        this.createUser = createUser;
    }

    public User register(String email, String password, String permission) {
        return createUser.execute(email, password, permission);
    }
}
