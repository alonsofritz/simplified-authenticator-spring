package com.alonsofritz.simplified_authenticator_spring.infra.persistence.mysql.repositories;

import com.alonsofritz.simplified_authenticator_spring.application.repositories.UserRepository;
import com.alonsofritz.simplified_authenticator_spring.infra.persistence.mysql.entities.UserEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository("userMySqlRepository")
public interface UserMySqlRepository extends JpaRepository<UserEntity, Long>, UserRepository {
    Optional<UserEntity> findByGlobalId(UUID globalId);
}