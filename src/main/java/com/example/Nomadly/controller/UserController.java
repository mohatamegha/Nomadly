package com.example.Nomadly.controller;

import com.example.Nomadly.entities.Travel;
import com.example.Nomadly.entities.User;
import com.example.Nomadly.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = {"localhost:5173","localhost:5174"})
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/health-check")
    public String health(){
        return "Working well";
    }

    @GetMapping
    public ResponseEntity<?> getAllUsers(){
        List<User> users = userService.getAllUsers();
        if(users!=null && !users.isEmpty())
            return new ResponseEntity<>(users, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getUserById(Authentication authentication){
        String email = authentication.getName();
        User user = userService.getUserByEmail(email);
        if(user!=null)
            return new ResponseEntity<>(user, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/me/travels")
    public ResponseEntity<?> getUserTravels(Authentication authentication){
        String email = authentication.getName();
        List<Travel> travels = userService.getUserTravels(email);
        if(travels!=null && !travels.isEmpty())
            return new ResponseEntity<>(travels, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> loginUser(@RequestParam String email,@RequestParam String password){
//        boolean exist= userService.checkUserExists(email);
//        if(!exist)
//            return new ResponseEntity<>("No user exists, signup first!",HttpStatus.NOT_FOUND);
//        else
//            return new ResponseEntity<>("Login Successful!",HttpStatus.OK);
//    }
//
//    @PostMapping("/signup")
//    public ResponseEntity<?> signupUser(@RequestBody User user){
////        moved this logic to the userService layer
////        boolean userExists = userService.checkUserExists(user.getEmail());
////        if(userExists) {
////            return new ResponseEntity<>("User already exists", HttpStatus.CONFLICT);
////        }
//
//        User savedUser = userService.signupUser(user);
//        if(savedUser!=null)
//            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//        else
//            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//    }
}
