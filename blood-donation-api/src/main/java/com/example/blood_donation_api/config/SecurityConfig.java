package com.example.blood_donation_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.blood_donation_api.security.JwtAuthenticationFilter;
import com.example.blood_donation_api.service.CustomUserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
public class SecurityConfig {

@Bean
public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
}

private final CustomUserDetailsService userDetailsService;

public SecurityConfig(
        CustomUserDetailsService userDetailsService,
        JwtAuthenticationFilter jwtAuthenticationFilter) {

    this.userDetailsService = userDetailsService;
    this.jwtAuthenticationFilter = jwtAuthenticationFilter;
}

private final JwtAuthenticationFilter jwtAuthenticationFilter;

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http)
        throws Exception {

    http
        .csrf(csrf -> csrf.disable())

        .userDetailsService(userDetailsService)

        .authorizeHttpRequests(auth -> auth
            .requestMatchers(
                    "/auth/**",
                    "/swagger-ui/**",
                    "/v3/api-docs/**",
                    "/swagger-ui.html"
            ).permitAll()

            .requestMatchers(HttpMethod.GET, "/donors")
            .hasAnyRole("USER", "ADMIN")

            .requestMatchers(HttpMethod.POST, "/donors")
            .hasAnyRole("USER", "ADMIN")

            .requestMatchers(HttpMethod.DELETE, "/donors/**")
            .hasRole("ADMIN")

            .requestMatchers(HttpMethod.GET, "/requests")
            .hasAnyRole("USER", "ADMIN")

            .requestMatchers(HttpMethod.POST, "/requests")
            .hasAnyRole("USER", "ADMIN")

            .anyRequest().authenticated()
        )

        .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter.class
        );

    return http.build();
}

}
