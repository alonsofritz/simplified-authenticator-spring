package com.alonsofritz.simplified_authenticator_spring.infra.persistence.mysql.repositories;

import com.alonsofritz.simplified_authenticator_spring.infra.persistence.mysql.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
}