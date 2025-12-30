package com.example.Nomadly.controller;

import com.example.Nomadly.entities.Travel;
import com.example.Nomadly.entities.User;
import com.example.Nomadly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String health(){
        return "Working well";
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        User user = userService.getUserById(id);
        if(user!=null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}/travels")
    public ResponseEntity<?> getUserTravels(@PathVariable Long id){
        List<Travel> travels = userService.getUserTravels(id);
        if(travels!=null && !travels.isEmpty())
            return new ResponseEntity<>(travels, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PostMapping("/signup")
    public ResponseEntity<?> signupUser(@RequestBody User user){
//        moved this logic to the userService layer
//        boolean userExists = userService.checkUserExists(user.getEmail());
//        if(userExists) {
//            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
//        }

        User savedUser = userService.signupUser(user);
        if(savedUser!=null)
            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
