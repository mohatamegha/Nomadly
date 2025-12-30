package com.example.Nomadly.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class PublicController {
    @RequestMapping("/home")
    public String home() {
        return "Welcome to Nomadly!";
    }
}
