package com.example.Nomadly.repository;

import com.example.Nomadly.entities.Travel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelRepo extends JpaRepository<Travel, Long> {
    List<Travel> findByDestination(String destination);
}
