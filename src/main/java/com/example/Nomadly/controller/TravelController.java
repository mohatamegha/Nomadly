package com.example.Nomadly.controller;

import com.example.Nomadly.entities.Travel;
import com.example.Nomadly.entities.User;
import com.example.Nomadly.entities.UserTravel;
import com.example.Nomadly.service.TravelService;
import com.example.Nomadly.service.UserService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/travels")
@CrossOrigin(origins = {"localhost:5173","localhost:5174"})
public class TravelController {

    private final TravelService travelService;
    private final UserService userService;

    @Autowired
    public TravelController(TravelService travelService, UserService userService) {
        this.travelService = travelService;
        this.userService = userService;
    }

    //checking if the endpoint is working as expected
    @GetMapping("/health-check")
    public String check() {
        return "Working A-Ok";
    }

    //Get all travels (Discover) : for the main screen
    @GetMapping
    public ResponseEntity<List<Travel>> getAllTravels(Authentication authentication) {
        String email = authentication.getName();
        List<Travel> travels = travelService.getAllTravels(email);
        return new ResponseEntity<>(travels, HttpStatus.OK);
    }

    //Get travel by ID: while chats and showing description at the time
    @GetMapping("/{id}")
    public ResponseEntity<?> getTravelById(@PathVariable Long id) {
        Travel travel = travelService.getTravelById(id);
        if (travel != null) {
            return new ResponseEntity<>(travel, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    // Search by destination
//    @GetMapping("/search")
//    public ResponseEntity<List<Travel>> getByDestination(@RequestParam String destination) {
//
//        List<Travel> travels = travelService.getTravelByDestination(destination);
//        return new ResponseEntity<>(travels, HttpStatus.OK);
//    }


    //enabling a user to add travel plans. (updateNeeded: travel much only be added if no such travel exists)
    @PostMapping
    public ResponseEntity<Travel> createTravel(@RequestBody Travel travel, Authentication authentication) {
        System.out.println("called travel controller");
        String email = authentication.getName();
        Travel savedTravel = travelService.addTravel(travel, email);
        return new ResponseEntity<>(savedTravel, HttpStatus.CREATED);
    }

    //combining a user to a travel plan
    @PostMapping("/{id}/join")
    public ResponseEntity<UserTravel> joinTravel(
            @PathVariable Long id,
            Authentication authentication) {

        String email = authentication.getName();
        UserTravel joined = travelService.joinTravel(email, id);
        return new ResponseEntity<>(joined, HttpStatus.CREATED);
    }
    @GetMapping("/{id}/count")
    public ResponseEntity<Long> countByTravel(@PathVariable Long id){
        Long count=travelService.countByTravel(id);
        return new ResponseEntity<>(count,HttpStatus.OK);
    }

    @GetMapping("/{id}/members")
    public ResponseEntity<List<User>> findMembersofTravel(@PathVariable Long id){
        List<User> users=travelService.findMembers(id);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> leaveTrip(@PathVariable Long id,Authentication authentication){
        String email=authentication.getName();
        User user=userService.getUserByEmail(email);
        return travelService.leaveTrip(id,user);
    }
    @GetMapping("/role")
    public ResponseEntity<String> findRole(@RequestParam Long userId,@RequestParam Long travelId){
        return travelService.getRole(userId,travelId);
    }

}
