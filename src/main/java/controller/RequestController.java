package com.example.blood_donation_api.controller;

import com.example.blood_donation_api.model.Request;
import com.example.blood_donation_api.repository.RequestRepository;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/requests")
@Validated
public class RequestController {

    private final RequestRepository requestRepository;

    public RequestController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @GetMapping
    public Page<Request> getRequests(
            @RequestParam String bloodGroup,
            @RequestParam String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sort) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sort));
        return requestRepository.findByBloodGroupAndCity(bloodGroup, city, pageable);
    }

    @PostMapping
    public Request addRequest(@Valid @RequestBody Request request) {
        return requestRepository.save(request);
    }
}
