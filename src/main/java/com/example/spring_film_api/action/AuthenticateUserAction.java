package com.example.spring_film_api.action;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Component;

import com.example.spring_film_api.dto.JwtResponse;
import com.example.spring_film_api.dto.LoginRequest;
import com.example.spring_film_api.model.User;
import com.example.spring_film_api.repository.UserRepository;
import com.example.spring_film_api.services.JwtService;
import jakarta.transaction.Transactional;

@Component
public class AuthenticateUserAction {
        private final AuthenticationManager authenticationManager;
        private final JwtService jwtService;
        private final UserRepository userRepository;

        public AuthenticateUserAction(AuthenticationManager authenticationManager,
                        JwtService jwtService, UserRepository userRepository) {
                this.authenticationManager = authenticationManager;
                this.jwtService = jwtService;
                this.userRepository = userRepository;
        }

        @Transactional
        public JwtResponse execute(LoginRequest request) {
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                                request.getUsername(), request.getPassword()));

                User user = this.userRepository.findByUsername(request.getUsername())
                                .orElseThrow(() -> new RuntimeException("User not found"));

                String jwt = jwtService.generateToken(user);

                return JwtResponse.builder().token(jwt).id(user.getId())
                                .username(user.getUsername())
                                .role(user.getAuthorities().iterator().next().getAuthority())
                                .build();
        }
}
