package com.example.blood_donation_api.repository;

import com.example.blood_donation_api.model.Donor;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DonorRepository extends JpaRepository<Donor, Long> {
    List<Donor> findByBloodGroupAndCity(String bloodGroup, String city);
}
