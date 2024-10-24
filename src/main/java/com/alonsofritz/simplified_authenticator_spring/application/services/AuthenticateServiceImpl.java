package com.alonsofritz.simplified_authenticator_spring.application.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.alonsofritz.simplified_authenticator_spring.adapters.mappers.UserMapper;
import com.alonsofritz.simplified_authenticator_spring.application.repositories.UserRepository;
import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;
import com.alonsofritz.simplified_authenticator_spring.domain.services.AuthenticateService;
import com.alonsofritz.simplified_authenticator_spring.infra.persistence.mysql.entities.UserEntity;
import com.alonsofritz.simplified_authenticator_spring.infra.security.token.JwtTokenProvider;

@Service("authenticateServiceImpl")
public class AuthenticateServiceImpl implements AuthenticateService {

    @Qualifier("userMySqlRepository")
    private final UserRepository userRepository;

    @Qualifier("jwtTokenProvider")
    private final JwtTokenProvider jwtTokenProvider;

    public AuthenticateServiceImpl(UserRepository userRepository, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String execute(String email, String password) {
        Optional<UserEntity> userOptional = userRepository.findByEmail(email);
        User user = UserMapper.toDomain(userOptional.orElse(null));
        
        if (userOptional.isEmpty()) {
            throw new RuntimeException("Invalid email or password");
        }

        System.out.println(email + " " + password);
        

        if (password.equals(user.getPassword())) {
            return jwtTokenProvider.generateToken(user);
        }
        return null;
    }
    
}
