package com.example.Nomadly.service;

import com.example.Nomadly.entities.Travel;
import com.example.Nomadly.entities.User;
import com.example.Nomadly.entities.UserTravel;
import com.example.Nomadly.repository.UserRepo;
import com.example.Nomadly.repository.UserTravelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepo userRepo;
    @Autowired
     TravelService travelService;
    @Autowired
    UserTravelRepo userTravelRepo;

    public User createUser(User user){
        return userRepo.save(user);
    }

    public User getUserById(Long id){
        return userRepo.findById(id).orElse(null);
    }


    public List<Travel> getUserTravels(String email) {
        User user = userRepo.findByEmail(email).orElse(null);
        //this validation will not be needed once login is implemented
//        if(user == null)
//            return null;

        //Find all the userTravel entries from the many-to-many table by giving user object
        List<UserTravel> userTravels =  userTravelRepo.findByUser(user);

        //the jpa maps the travel object from the travel ids stored and we get all the travel objects ultimately.
        List<Travel> travel= userTravels.stream()
                .map(userTravel -> userTravel.getTravel())
                .toList();
        for(Travel t:travel){
            long count=travelService.getMemberCount(t);
            t.setMembersJoined((int)count);
        }
        return travel;
    }

    public boolean checkUserExists(String email) {
        Optional<User> user = userRepo.findByEmail(email);
        return user.isPresent();
    }

    public User signupUser(User user) {
        boolean userExists = checkUserExists(user.getEmail());
        if(userExists)
            throw new RuntimeException("User already exists with this email");
        user.setDateJoined(LocalDateTime.now().toLocalDate().toString());
        //user does not exist, so create new user
        //to be added: checking email and password validity
        return userRepo.save(user);
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserByEmail(String email) {
        return userRepo.findByEmail(email).orElse(null);
    }
}