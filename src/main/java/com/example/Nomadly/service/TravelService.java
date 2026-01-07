package com.example.Nomadly.service;

import com.example.Nomadly.entities.Travel;
import com.example.Nomadly.entities.User;
import com.example.Nomadly.entities.UserTravel;
import com.example.Nomadly.repository.TravelRepo;
import com.example.Nomadly.repository.UserRepo;
import com.example.Nomadly.repository.UserTravelRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

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
    public Travel addTravel(Travel travel,String email){
        User user = userRepo.findByEmail(email).orElseThrow(()->new RuntimeException("User not found"));
//        User user = userRepo.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
        travel.setCreatedBy(user);
        travelRepo.save(travel);
        joinTravel(email,travel.getTravelId());
        return travel;
    }

    public long getMemberCount(Travel travel) {
        long count = userTravelRepo.countByTravel(travel);
        return count;
    }

    public List<Travel> getAllTravels(){
        List<Travel> travels = travelRepo.findAll();
        List<Travel> responseList = travels.stream().map( (travel -> {
            long count = getMemberCount(travel);
            travel.setMembersJoined((int)count);
            return travel;
        })).toList();

        return responseList;
    }

//    public List<Travel> getTravelByDestination(String destination){
//        List<Travel> travelList = travelRepo.findByDestination(destination);
//        return travelList;
//    }

    public Travel getTravelById(Long id){
        Optional<Travel> travel = travelRepo.findById(id);
        int count = (int) getMemberCount(travel.get());
        travel.get().setMembersJoined(count);
        return travel.orElse(null);
        //some change made on a diff branch
//        if(travel.isPresent())
//            return travel.get();
//        return null;
    }

    public UserTravel joinTravel(String email, Long travelId){
        Optional<Travel> travel = travelRepo.findById(travelId);
        if(travel.isEmpty())
            throw new RuntimeException("No such trip exists");
        Optional<User> user = userRepo.findByEmail(email);
        if(user.isEmpty()) //this check won't be needed when the login functionality is added.. because there will always be a valid user
            throw new RuntimeException("User not found");

        boolean joined = userTravelRepo.existsByUserAndTravel(user.get(), travel.get());

        if(joined)
            throw new RuntimeException("Already joined the trip! ");

        Long count = userTravelRepo.countByTravel_TravelId(travel.get().getTravelId());

        if(count>=travel.get().getGroupSize())
            throw new RuntimeException("Already at max capacity.");

        String role = count==0?"CREATOR":"MEMBER";
        LocalDateTime now = LocalDateTime.now();
        UserTravel userTravel = new UserTravel(user.get(), travel.get(), role, now);
        userTravel = userTravelRepo.save(userTravel);
        return userTravel;
    }


    public Long countByTravel(Long id) {
        return userTravelRepo.countByTravel_TravelId(id);
    }


    public List<User> findMembers(Long id) {
        List<UserTravel>userTravels= userTravelRepo.findByTravel_TravelId(id);
        return userTravels.stream()
                .map(userTravel -> userTravel.getUser())
                .toList();
    }
    public ResponseEntity<String> getRole(Long userId,Long travelId){
        String role=userTravelRepo.findRoleByUserAndTravel(userId,travelId);
        return new ResponseEntity<>(role, HttpStatus.OK);
    }
}
