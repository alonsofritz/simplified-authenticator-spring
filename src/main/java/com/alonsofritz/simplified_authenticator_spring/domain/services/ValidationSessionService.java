package com.alonsofritz.simplified_authenticator_spring.domain.services;

public interface ValidationSessionService {
    boolean execute(String token);
}
