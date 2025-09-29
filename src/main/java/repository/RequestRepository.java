package com.example.blood_donation_api.repository;

import com.example.blood_donation_api.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RequestRepository extends JpaRepository<Request, Long> {
    List<Request> findByBloodGroupAndCity(String bloodGroup, String city);
}
