package com.example.blood_donation_api.controller;

import com.example.blood_donation_api.dto.AuthResponse;
import com.example.blood_donation_api.dto.RegisterRequest;
import com.example.blood_donation_api.model.Role;
import com.example.blood_donation_api.model.User;
import com.example.blood_donation_api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;

public AuthController(UserRepository userRepository,
                      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
}

@PostMapping("/register")
public AuthResponse register(@Valid @RequestBody RegisterRequest request) {

    if (userRepository.existsByUsername(request.getUsername())) {
        throw new RuntimeException("Username already exists");
    }

    if (userRepository.existsByEmail(request.getEmail())) {
        throw new RuntimeException("Email already exists");
    }

    User user = new User();

    user.setUsername(request.getUsername());
    user.setEmail(request.getEmail());

    user.setPassword(
            passwordEncoder.encode(request.getPassword())
    );

    user.setRole(Role.USER);

    userRepository.save(user);

    return new AuthResponse("User registered successfully");
}

}
