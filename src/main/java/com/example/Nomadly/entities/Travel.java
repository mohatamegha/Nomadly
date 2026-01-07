package com.example.Nomadly.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "travel")
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelId;
    //add field to store the uploaded image URL
    private String imageUrl;
    private String destination;
    private LocalDate startDate;
    private int duration;
    private double budget;
    private String travelType; //not used anywhere as yet
    private String description;
    private int groupSize;
    private int membersJoined;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
}
