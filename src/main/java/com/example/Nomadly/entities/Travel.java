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

    public Travel() {
    }

    public Travel(Long travelId, String destination, LocalDate startDate, int duration, double budget, String travelType, String description, int groupSize, User createdBy) {
        this.travelId = travelId;
        this.destination = destination;
        this.startDate = startDate;
        this.duration = duration;
        this.budget = budget;
        this.travelType = travelType;
        this.description = description;
        this.groupSize = groupSize;
        this.createdBy = createdBy;
    }

    public Long getTravelId() {
        return travelId;
    }

    public void setTravelId(Long travelId) {
        this.travelId = travelId;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getTravelType() {
        return travelType;
    }

    public void setTravelType(String travelType) {
        this.travelType = travelType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getGroupSize() {
        return groupSize;
    }

    public void setGroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }
}
