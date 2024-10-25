package com.alonsofritz.simplified_authenticator_spring.application.usecases.user;

import com.alonsofritz.simplified_authenticator_spring.application.repositories.UserRepository;
import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("authenticateUser")
public class AuthenticateUser {

    private final UserRepository userRepository;

    public AuthenticateUser(
            @Qualifier("userMySqlRepository") UserRepository userRepository
    ) {
        this.userRepository = userRepository;
    }

    public User execute(String email, String password) {

        User user = userRepository.findByEmail(email);
        if (user == null) {
            throw new RuntimeException("No user found with email " + email);
        }

        boolean passwordMatches = user.getPassword().equals(password);
        if (!passwordMatches) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
