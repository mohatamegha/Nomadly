package com.example.Nomadly.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_travel")

public class UserTravel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public UserTravel(User user, Travel travel, String role, LocalDateTime joinedAt) {
        this.user = user;
        this.travel = travel;
        this.role = role;
        this.joinedAt = joinedAt;
    }

    @ManyToOne
    @JoinColumn(name = "travel_id")
    private Travel travel;

    private String role;
    private LocalDateTime joinedAt;
public UserTravel(){

}
    public UserTravel(Long id, User user, Travel travel, String role, LocalDateTime joinedAt) {
        this.id = id;
        this.user = user;
        this.travel = travel;
        this.role = role;
        this.joinedAt = joinedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getJoinedAt() {
        return joinedAt;
    }

    public void setJoinedAt(LocalDateTime joinedAt) {
        this.joinedAt = joinedAt;
    }
}
