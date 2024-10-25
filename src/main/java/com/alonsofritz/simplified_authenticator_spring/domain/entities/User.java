package com.alonsofritz.simplified_authenticator_spring.domain.entities;

import java.util.Set;
import java.util.UUID;

public class User {
    private Long id;
    private final UUID globalId;
    private final String email;
    private final String password;
    private final Set<String> permissions;

    public User(String email, String password, Set<String> permissions) {
        this.globalId = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.permissions = permissions;
    }

    public User(Long id, String email, String password, Set<String> permissions) {
        this.id = id;
        this.globalId = UUID.randomUUID();
        this.email = email;
        this.password = password;
        this.permissions = permissions;
    }

    public User(Long id, UUID globalId, String email, String password, Set<String> permissions) {
        this.id = id;
        this.globalId = globalId;
        this.email = email;
        this.password = password;
        this.permissions = permissions;
    }

    public Long getId() {
        return id;
    }

    public UUID getGlobalId() {
        return globalId;
    }

    public String getEmail() {
        return email;
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
