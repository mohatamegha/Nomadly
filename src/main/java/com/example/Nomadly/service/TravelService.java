package com.example.Nomadly.service;

import com.example.Nomadly.entities.Travel;
import com.example.Nomadly.entities.User;
import com.example.Nomadly.entities.UserTravel;
import com.example.Nomadly.repository.TravelRepo;
import com.example.Nomadly.repository.UserRepo;
import com.example.Nomadly.repository.UserTravelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TravelService {

    @Autowired
    TravelRepo travelRepo;

    @Autowired
    UserTravelRepo userTravelRepo;

    @Autowired
    UserRepo userRepo;

    //to add a trip
    public Travel addTravel(Travel travel){
        return travelRepo.save(travel);
    }

    public List<Travel> getAllTravels(){
        return travelRepo.findAll();
    }

    public List<Travel> getTravelByDestination(String destination){
        List<Travel> travelList = travelRepo.findByDestination(destination);
        return travelList;
    }

    public Travel getTravelById(Long id){
        Optional<Travel> travel = travelRepo.findById(id);
        return travel.orElse(null);
//        if(travel.isPresent())
//            return travel.get();
//        return null;
    }

    public UserTravel joinTravel(Long userId, Long travelId){
        Optional<Travel> travel = travelRepo.findById(travelId);
        if(travel.isEmpty())
            throw new RuntimeException("No such trip exists");
        Optional<User> user = userRepo.findById(userId);
        if(user.isEmpty()) //this check won't be needed when the login functionality is added.. because there will always be a valid user
            throw new RuntimeException("User not found");

        boolean joined = userTravelRepo.existsByUserAndTravel(user.get(), travel.get());

        if(joined)
            throw new RuntimeException("Already joined the trip! ");

        long count = userTravelRepo.countByTravel(travel.get());

        if(count>=travel.get().getGroupSize())
            throw new RuntimeException("Already at max capacity.");




        UserTravel userTravel =


    }




}
