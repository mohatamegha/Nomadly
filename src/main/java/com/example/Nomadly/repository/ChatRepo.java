package com.example.Nomadly.repository;

import com.example.Nomadly.entities.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatRepo extends JpaRepository<ChatMessage,Long> {
    List<ChatMessage> findByTravel_TravelIdOrderByTimestampAsc(Long travelId);
}
