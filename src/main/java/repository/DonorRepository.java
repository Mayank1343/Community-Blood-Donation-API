package com.example.blood_donation_api.repository;
import com.example.blood_donation_api.model.Donor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.blood_donation_api.model.Donor;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    Page<Donor> findByBloodGroupAndCity(String bloodGroup, String city, Pageable pageable);
}
