package com.example.blood_donation_api.repository;

import com.example.blood_donation_api.model.Request;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RequestRepository extends JpaRepository<Request, Long> {
    Page<Request> findByBloodGroupAndCity(String bloodGroup, String city, Pageable pageable);
}
