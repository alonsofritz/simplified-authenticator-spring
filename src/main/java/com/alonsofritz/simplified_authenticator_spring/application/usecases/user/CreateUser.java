package com.alonsofritz.simplified_authenticator_spring.application.usecases.user;

import com.alonsofritz.simplified_authenticator_spring.application.repositories.UserRepository;
import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;
import com.alonsofritz.simplified_authenticator_spring.domain.factory.UserFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component("createUser")
public class CreateUser {

    private final UserRepository userRepository;

    public CreateUser(@Qualifier("userMySqlRepository") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User execute(String email, String password, String permission) {
        User user = UserFactory.create(email, password, Set.of(permission));
        userRepository.save(user);
        return user;
    }
}
