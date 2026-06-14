package com.example.blood_donation_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.blood_donation_api.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

Optional<User> findByUsername(String username);

boolean existsByUsername(String username);

boolean existsByEmail(String email);

}

