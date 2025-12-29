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

    private String name;
    private String email;
    private String phone;
    private String password;
    private String about;
    private String currentCity;
    private String currentCountry;
    private String photo;
}



