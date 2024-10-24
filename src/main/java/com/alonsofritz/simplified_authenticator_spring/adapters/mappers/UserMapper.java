package com.alonsofritz.simplified_authenticator_spring.adapters.mappers;


import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;
import com.alonsofritz.simplified_authenticator_spring.infra.persistence.mysql.entities.UserEntity;

public class UserMapper {

    public static User toDomain(UserEntity userEntity) {
        User user = new User(userEntity.getId(), userEntity.getGlobalId(), userEntity.getEmail(), userEntity.getPassword(), null);
        return user;
    }

    public static UserEntity toEntity(User user) {
        UserEntity userEntity = new UserEntity();
        userEntity.setGlobalId(user.getGlobalId());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        return userEntity;
    }
}