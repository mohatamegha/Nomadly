package com.example.Nomadly.repository;

import com.example.Nomadly.entities.Travel;
import com.example.Nomadly.entities.User;
import com.example.Nomadly.entities.UserTravel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTravelRepo extends JpaRepository<UserTravel, Long> {
    List<UserTravel> findByUser_UserId(Long userId);

    List<UserTravel> findByTravel_TravelId(Long travelId);

    boolean existsByUserAndTravel(User user, Travel travel);

    long countByTravel(Travel travel);
}
