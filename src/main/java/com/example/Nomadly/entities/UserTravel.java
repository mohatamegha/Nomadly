package com.example.Nomadly.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_travel")
@AllArgsConstructor
@NoArgsConstructor
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
}
