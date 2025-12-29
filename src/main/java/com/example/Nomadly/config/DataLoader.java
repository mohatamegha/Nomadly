package com.example.Nomadly.config;

import com.example.Nomadly.entities.Travel;
import com.example.Nomadly.entities.User;
import com.example.Nomadly.entities.UserTravel;
import com.example.Nomadly.repository.TravelRepo;
import com.example.Nomadly.repository.UserRepo;
import com.example.Nomadly.repository.UserTravelRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(
            UserRepo userRepository,
            TravelRepo travelRepository,
            UserTravelRepo userTravelRepository
    ) {
        return args -> {

            // 1️⃣ Create Users
            User user1 = new User();
            user1.setName("Alice");
            user1.setEmail("alice@test.com");
            user1.setCurrentCity("Mumbai");
            user1.setCurrentCountry("India");

            User user2 = new User();
            user2.setName("Bob");
            user2.setEmail("bob@test.com");
            user2.setCurrentCity("Delhi");
            user2.setCurrentCountry("India");

            userRepository.save(user1);
            userRepository.save(user2);

            // 2️⃣ Create Travel
            Travel travel = new Travel();
            travel.setDestination("Goa");
            travel.setStartDate(LocalDate.of(2025, 2, 10));
            travel.setDuration(5);
            travel.setBudget(15000);
            travel.setGroupSize(4);
            travel.setDescription("Goa beach trip");
            travel.setCreatedBy(user1);

            travelRepository.save(travel);

            // 3️⃣ Create UserTravel (join table)
            UserTravel userTravel = new UserTravel();
            userTravel.setUser(user1);
            userTravel.setTravel(travel);
            userTravel.setRole("CREATOR");
            userTravel.setJoinedAt(LocalDateTime.now());

            userTravelRepository.save(userTravel);

            System.out.println("✅ Test data loaded successfully");
        };
    }
}
