package com.example.Nomadly.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(
        name = "chat_message",
        indexes = {
                @Index(name = "idx_chat_travel", columnList = "travel_id"),
                @Index(name = "idx_chat_timestamp", columnList = "timestamp")
        }
)
public class ChatMessage {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name="travel_id")
    private Travel travel;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(length=1000)
    private String content;
    private LocalDateTime timestamp;

    public ChatMessage() {
    }

    public ChatMessage(Long id, Travel travel, User user, String content, LocalDateTime timestamp) {
        this.id = id;
        this.travel = travel;
        this.user = user;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Travel getTravel() {
        return travel;
    }

    public void setTravel(Travel travel) {
        this.travel = travel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
