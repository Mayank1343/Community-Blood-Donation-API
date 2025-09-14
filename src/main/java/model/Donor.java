package com.example.blood_donation_api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Donor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String bloodGroup;
    private String city;
    private String phone;

    // Getters and Setters (for all fields)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
