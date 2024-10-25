package com.alonsofritz.simplified_authenticator_spring.domain.factory;

import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;

import java.util.Set;
import java.util.UUID;

public class UserFactory {
    public static User create(String email, String password, Set<String> permissions) {

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        if (permissions == null || permissions.isEmpty()) {
            throw new IllegalArgumentException("Permissions cannot be null or empty");
        }

        return new User(email, password, permissions);
    }

    public static User create(Long id, String email, String password, Set<String> permissions) {

        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        if (permissions == null || permissions.isEmpty()) {
            throw new IllegalArgumentException("Permissions cannot be null or empty");
        }

        return new User(id, email, password, permissions);
    }

    public static User inflate(Long id, UUID globalId, String email, String password, Set<String> permissions) {

        if (id == null) {
            throw new IllegalArgumentException("Id cannot be null");
        }

        if (email == null || email.isEmpty()) {
            throw new IllegalArgumentException("Username cannot be null or empty");
        }

        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        if (permissions == null || permissions.isEmpty()) {
            throw new IllegalArgumentException("Permissions cannot be null or empty");
        }

        if (globalId == null) {
            throw new IllegalArgumentException("GlobalId cannot be null");
        }

        return new User(id, globalId, email, password, permissions);
    }
}
