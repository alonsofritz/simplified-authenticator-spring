package com.alonsofritz.simplified_authenticator_spring.application.repositories;

import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;

public interface UserRepository {
    void save(User user);
    User findByEmail(String email);
}
