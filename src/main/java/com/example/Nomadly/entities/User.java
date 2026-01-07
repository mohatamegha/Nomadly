package com.example.Nomadly.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name; //at the time of signup
    private String email; //signup
    private String phone;
    private String password; //signup
    private String about;
    private String currentCity;
    private String currentCountry;
    private String photo;
    private String dateJoined; //signup
}



