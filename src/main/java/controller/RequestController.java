package com.example.blood_donation_api.controller;

import com.example.blood_donation_api.model.Request;
import com.example.blood_donation_api.repository.RequestRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestRepository requestRepository;

    public RequestController(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @GetMapping
    public List<Request> getRequests(@RequestParam String bloodGroup, @RequestParam String city) {
        return requestRepository.findByBloodGroupAndCity(bloodGroup, city);
    }

    @PostMapping
    public Request addRequest(@RequestBody Request request) {
        return requestRepository.save(request);
    }
}
