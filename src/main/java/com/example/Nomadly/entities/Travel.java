package com.example.Nomadly.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Table(name = "travel")
public class Travel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long travelId;

    private String destination;
    private LocalDate startDate;
    private int duration;
    private double budget;
    private String travelType;
    private String description;
    private int groupSize;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;
}
