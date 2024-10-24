package com.alonsofritz.simplified_authenticator_spring.application.services;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alonsofritz.simplified_authenticator_spring.adapters.mappers.UserMapper;
import com.alonsofritz.simplified_authenticator_spring.application.repositories.UserRepository;
import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;
import com.alonsofritz.simplified_authenticator_spring.domain.services.SignUpService;
import com.alonsofritz.simplified_authenticator_spring.infra.persistence.mysql.entities.UserEntity;

import java.util.Set;

@Service("signUpServiceImpl")
public class SignUpServiceImpl implements SignUpService {
    
    @Qualifier("userMySqlRepository")
    private final UserRepository userRepository;

    public SignUpServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public User execute(String email, String password, String permission) {
        User user = new User(email, password, Set.of(permission));
        UserEntity userEntity = UserMapper.toEntity(user);
        userRepository.save(userEntity);
        return user;
    }
    
}
