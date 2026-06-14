package com.example.blood_donation_api.controller;

import com.example.blood_donation_api.dto.AuthResponse;
import com.example.blood_donation_api.dto.RegisterRequest;
import com.example.blood_donation_api.model.Role;
import com.example.blood_donation_api.model.User;
import com.example.blood_donation_api.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.blood_donation_api.dto.LoginRequest;
import com.example.blood_donation_api.dto.LoginResponse;
import com.example.blood_donation_api.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RestController
@RequestMapping("/auth")
public class AuthController {

private final UserRepository userRepository;
private final PasswordEncoder passwordEncoder;
private final AuthenticationManager authenticationManager;
private final JwtService jwtService;


public AuthController(
        AuthenticationManager authenticationManager,
        JwtService jwtService,
        PasswordEncoder passwordEncoder,
        UserRepository userRepository) {

    this.authenticationManager = authenticationManager;
    this.jwtService = jwtService;
    this.passwordEncoder = passwordEncoder;
    this.userRepository = userRepository;
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

@PostMapping("/login")
public LoginResponse login(
@Valid @RequestBody LoginRequest request) {

authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
                request.getUsername(),
                request.getPassword()
        )
);

String token = jwtService.generateToken(
        request.getUsername()
);

return new LoginResponse(token);
}

}
