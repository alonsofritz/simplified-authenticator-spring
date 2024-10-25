package com.alonsofritz.simplified_authenticator_spring.infra.persistence.mysql.repositories;

import com.alonsofritz.simplified_authenticator_spring.application.repositories.UserRepository;
import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;
import com.alonsofritz.simplified_authenticator_spring.infra.persistence.mysql.entities.UserEntity;
import com.alonsofritz.simplified_authenticator_spring.adapters.mappers.UserMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("userMySqlRepository")
public class UserMySqlRepository implements UserRepository {

    private final SpringDataUserRepository springDataUserRepository;

    public UserMySqlRepository(SpringDataUserRepository springDataUserRepository) {
        this.springDataUserRepository = springDataUserRepository;
    }

    @Override
    public void save(User user) {
        UserEntity userEntity = UserMapper.toEntity(user);
        springDataUserRepository.save(userEntity);
    }

    @Override
    public User findByEmail(String email) {
        Optional<UserEntity> userEntity = springDataUserRepository.findByEmail(email);
        return userEntity.map(UserMapper::toDomain).orElse(null);
    }
}