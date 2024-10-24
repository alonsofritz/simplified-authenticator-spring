package com.alonsofritz.simplified_authenticator_spring.application.repositories;

import com.alonsofritz.simplified_authenticator_spring.infra.persistence.mysql.entities.UserEntity;

import java.util.UUID;
import java.util.Optional;

public interface UserRepository {
    UserEntity save(UserEntity user);
    Optional<UserEntity> findByGlobalId(UUID globalId);
}
