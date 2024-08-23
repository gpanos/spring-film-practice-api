package com.example.spring_film_api.action;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.example.spring_film_api.dto.RegisterUserRequest;
import com.example.spring_film_api.enumeration.ERole;
import com.example.spring_film_api.model.User;
import com.example.spring_film_api.repository.UserRepository;

import jakarta.transaction.Transactional;

@Component
public class RegisterUserAction {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserAction(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public void execute(RegisterUserRequest request) {
        User user = new User();

        user.setUsername(request.getUsername());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(ERole.USER);

        userRepository.save(user);
    }
}
