package com.example.Nomadly.controller;

import com.example.Nomadly.entities.Travel;
import com.example.Nomadly.entities.UserTravel;
import com.example.Nomadly.service.TravelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/travels")
public class TravelController {

    private final TravelService travelService;

    @Autowired
    public TravelController(TravelService travelService) {
        this.travelService = travelService;
    }

    //checking if the endpoint is working as expected
    @GetMapping("/health-check")
    public String check() {
        return "Working A-Ok";
    }

    //Get all travels (Discover) : for the main screen
    @GetMapping
    public ResponseEntity<List<Travel>> getAllTravels() {
        List<Travel> travels = travelService.getAllTravels();
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

    // Search by destination
    @GetMapping("/search")
    public ResponseEntity<List<Travel>> getByDestination(@RequestParam String destination) {

        List<Travel> travels = travelService.getTravelByDestination(destination);
        return new ResponseEntity<>(travels, HttpStatus.OK);
    }


    //enabling a user to add travel plans. (updateNeeded: travel much only be added if no such travel exists)
    @PostMapping
    public ResponseEntity<Travel> createTravel(@RequestBody Travel travel) {
        Travel savedTravel = travelService.addTravel(travel);
        return new ResponseEntity<>(savedTravel, HttpStatus.CREATED);
    }

    //combining a user to a travel plan
    @PostMapping("/{id}/join")
    public ResponseEntity<UserTravel> joinTravel(
            @PathVariable Long id,
            @RequestParam Long userId) {

        UserTravel joined = travelService.joinTravel(userId, id);
        return new ResponseEntity<>(joined, HttpStatus.CREATED);
    }

}
