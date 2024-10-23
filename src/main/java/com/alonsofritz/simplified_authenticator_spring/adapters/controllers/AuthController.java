package com.alonsofritz.simplified_authenticator_spring.adapters.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alonsofritz.simplified_authenticator_spring.adapters.dtos.LoginRequestDto;
import com.alonsofritz.simplified_authenticator_spring.adapters.dtos.LoginResponseDto;
import com.alonsofritz.simplified_authenticator_spring.domain.services.AuthenticateService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Qualifier("authenticateServiceImpl")
    private final AuthenticateService authenticateService;

    public AuthController(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {

        String token = authenticateService.execute(loginRequestDto.username(), loginRequestDto.password());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    // @GetMapping("/verify")
    // public ResponseEntity<VerifyTokenResponseDto> validate(@RequestHeader("Authorization") String token) {
    //     if (token == null || token.isEmpty()) {
    //         return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Token not found");
    //     }
    
    //     String claims = jwtTokenProvider.getClaimsFromToken(token);
    //     return ResponseEntity.ok(claims);
    // }
    // 
    // @PostMapping("/register")
    // public ResponseEntity<?> register(@RequestBody User user) {
    //     User userFound = userService.findByEmail(user.getEmail());
    //     if (userFound != null) {
    //         return ResponseEntity.status(HttpStatus.CONFLICT).body("User already exists");
    //     }
    // 
    //     userService.save(user);
    //     return ResponseEntity.ok("User created");
    // }
    // 
}
