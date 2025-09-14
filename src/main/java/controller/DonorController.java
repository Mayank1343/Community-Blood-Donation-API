package com.example.blood_donation_api.controller;

import com.example.blood_donation_api.model.Donor;
import com.example.blood_donation_api.repository.DonorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/donors")
public class DonorController {

    private final DonorRepository donorRepository;

    public DonorController(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    @GetMapping
    public List<Donor> getDonors(@RequestParam String bloodGroup, @RequestParam String city) {
        return donorRepository.findByBloodGroupAndCity(bloodGroup, city);
    }

    @PostMapping
    public Donor addDonor(@RequestBody Donor donor) {
        return donorRepository.save(donor);
    }
}
