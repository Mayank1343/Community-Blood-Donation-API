package com.example.blood_donation_api.controller;

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
            @RequestParam(required = false) String bloodGroup,
            @RequestParam(required = false) String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));

        boolean hasBloodGroup = bloodGroup != null && !bloodGroup.isBlank();
        boolean hasCity = city != null && !city.isBlank();

        if (!hasBloodGroup && !hasCity) {
            return donorRepository.findAll(pageable);
        }

        if (hasBloodGroup && !hasCity) {
            return donorRepository.findByBloodGroup(bloodGroup, pageable);
        }

        if (!hasBloodGroup && hasCity) {
            return donorRepository.findByCity(city, pageable);
        }

        return donorRepository.findByBloodGroupAndCity(bloodGroup, city, pageable);
    }

    @PostMapping
    public Donor addDonor(@Valid @RequestBody Donor donor) {
        return donorRepository.save(donor);
    }

    @DeleteMapping("/{id}")
    public void deleteDonor(@PathVariable Long id) {
        donorRepository.deleteById(id);
    }
}
