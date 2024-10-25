package com.alonsofritz.simplified_authenticator_spring.adapters.controllers;

import com.alonsofritz.simplified_authenticator_spring.application.services.AuthService;
import com.alonsofritz.simplified_authenticator_spring.domain.entities.Token;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alonsofritz.simplified_authenticator_spring.adapters.dtos.LoginRequestDto;
import com.alonsofritz.simplified_authenticator_spring.adapters.dtos.LoginResponseDto;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Qualifier("authService")
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {

        Token token = authService.login(loginRequestDto.email(), loginRequestDto.password());
        return ResponseEntity.ok(new LoginResponseDto(token.getAccessToken()));
    }
}
