package com.example.spring_film_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_film_api.action.AuthenticateUserAction;
import com.example.spring_film_api.action.RegisterUserAction;
import com.example.spring_film_api.dto.JwtResponse;
import com.example.spring_film_api.dto.LoginRequest;
import com.example.spring_film_api.dto.RegisterUserRequest;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final RegisterUserAction registerUserAction;
    private final AuthenticateUserAction authenticateUserAction;

    public AuthController(RegisterUserAction registerUserAction,
            AuthenticateUserAction authenticateUserAction) {
        this.registerUserAction = registerUserAction;
        this.authenticateUserAction = authenticateUserAction;
    }

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok().body(authenticateUserAction.execute(request));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterUserRequest request) {
        registerUserAction.execute(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
