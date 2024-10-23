package com.alonsofritz.simplified_authenticator_spring.domain.entities;

import java.util.Set;

public class User {
    private String username;
    private String password;
    private Set<String> permissions;

    public User(String username, String password, Set<String> permissions) {
        this.username = username;
        this.password = password;
        this.permissions = permissions;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public boolean hasPermission(String permission) {
        return permissions.contains(permission);
    }
}
