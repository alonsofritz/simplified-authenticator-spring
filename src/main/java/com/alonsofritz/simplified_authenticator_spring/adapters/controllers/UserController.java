package com.alonsofritz.simplified_authenticator_spring.adapters.controllers;

import com.alonsofritz.simplified_authenticator_spring.adapters.dtos.SignUpRequestDto;
import com.alonsofritz.simplified_authenticator_spring.adapters.dtos.SignUpResponseDto;
import com.alonsofritz.simplified_authenticator_spring.application.services.UserService;
import com.alonsofritz.simplified_authenticator_spring.domain.entities.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Qualifier("userService")
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto signUpRequestDto) {

        User user = userService.register(signUpRequestDto.email(), signUpRequestDto.password(), signUpRequestDto.permission());
        return ResponseEntity.ok(new SignUpResponseDto(user.getGlobalId(), user.getEmail(), user.getPermissions()));
    }
}
