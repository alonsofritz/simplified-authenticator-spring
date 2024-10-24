package com.alonsofritz.simplified_authenticator_spring.adapters.controllers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alonsofritz.simplified_authenticator_spring.adapters.dtos.LoginRequestDto;
import com.alonsofritz.simplified_authenticator_spring.adapters.dtos.LoginResponseDto;
import com.alonsofritz.simplified_authenticator_spring.adapters.dtos.SignUpRequestDto;
import com.alonsofritz.simplified_authenticator_spring.domain.services.AuthenticateService;
import com.alonsofritz.simplified_authenticator_spring.domain.services.SignUpService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    
    @Qualifier("authenticateServiceImpl")
    private final AuthenticateService authenticateService;

    @Qualifier("signUpServiceImpl")
    private final SignUpService signUpService;

    public AuthController(AuthenticateService authenticateService, SignUpService signUpService) {
        this.authenticateService = authenticateService;
        this.signUpService = signUpService;
    }


    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto) {

        String token = authenticateService.execute(loginRequestDto.email(), loginRequestDto.password());
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        signUpService.execute(signUpRequestDto.email(), signUpRequestDto.password(), signUpRequestDto.permission());
        return ResponseEntity.ok("User created");
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
