package com.example.blood_donation_api.controller;
import com.example.blood_donation_api.model.Donor;
import com.example.blood_donation_api.model.Donor;
import com.example.blood_donation_api.repository.DonorRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/donors")
@Validated
public class DonorController {

    private final DonorRepository donorRepository;

    public DonorController(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    @GetMapping
    public Page<Donor> getDonors(
            @RequestParam String bloodGroup,
            @RequestParam String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return donorRepository.findByBloodGroupAndCity(bloodGroup, city, pageable);
    }

    @PostMapping
    public Donor addDonor(@Valid @RequestBody Donor donor) {
        return donorRepository.save(donor);
    }
}
