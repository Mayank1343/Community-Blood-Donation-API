package com.example.blood_donation_api.config;

import com.example.blood_donation_api.model.Donor;
import com.example.blood_donation_api.repository.DonorRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeeder {

    @Bean
    CommandLineRunner seedDonors(DonorRepository donorRepository) {

        return args -> {

            String[][] donors = {
                    {"Rahul Sharma", "A+", "Dehradun", "9876543201"},
                    {"Priya Singh", "B+", "Delhi", "9876543202"},
                    {"Amit Verma", "O-", "Mumbai", "9876543203"},
                    {"Sneha Joshi", "AB+", "Bangalore", "9876543204"},
                    {"Rohit Gupta", "A-", "Chandigarh", "9876543205"},
                    {"Anjali Mehta", "O+", "Jaipur", "9876543206"},
                    {"Vikas Kumar", "B-", "Lucknow", "9876543207"},
                    {"Pooja Rawat", "AB-", "Dehradun", "9876543208"},
                    {"Karan Malhotra", "A+", "Pune", "9876543209"},
                    {"Neha Kapoor", "B+", "Noida", "9876543210"},
                    {"Arjun Nair", "O+", "Kochi", "9876543211"},
                    {"Simran Kaur", "AB+", "Amritsar", "9876543212"},
                    {"Manish Pandey", "A-", "Varanasi", "9876543213"},
                    {"Isha Arora", "B-", "Gurgaon", "9876543214"},
                    {"Deepak Yadav", "O-", "Patna", "9876543215"},
                    {"Nidhi Sharma", "A+", "Indore", "9876543216"},
                    {"Saurabh Jain", "AB-", "Bhopal", "9876543217"},
                    {"Kritika Bansal", "O+", "Meerut", "9876543218"},
                    {"Aditya Rana", "B+", "Dehradun", "9876543219"},
                    {"Megha Saxena", "A-", "Kanpur", "9876543220"},
                    {"Harsh Vardhan", "AB+", "Hyderabad", "9876543221"},
                    {"Ritika Sethi", "O-", "Chennai", "9876543222"},
                    {"Yash Agarwal", "B-", "Ahmedabad", "9876543223"},
                    {"Tanvi Bhatt", "A+", "Nainital", "9876543224"},
                    {"Mohit Chauhan", "O+", "Haridwar", "9876543225"}
            };

            for (String[] data : donors) {

                if (!donorRepository.existsByPhone(data[3])) {

                    Donor donor = new Donor();

                    donor.setName(data[0]);
                    donor.setBloodGroup(data[1]);
                    donor.setCity(data[2]);
                    donor.setPhone(data[3]);

                    donorRepository.save(donor);
                }
            }

            System.out.println("Demo donor synchronization completed.");
        };
    }
}