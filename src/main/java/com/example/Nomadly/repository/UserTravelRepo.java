package com.example.Nomadly.repository;

import com.example.Nomadly.entities.Travel;
import com.example.Nomadly.entities.User;
import com.example.Nomadly.entities.UserTravel;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTravelRepo extends JpaRepository<UserTravel, Long> {
    List<UserTravel> findByUser(User user);

    List<UserTravel> findByTravel_TravelId(Long travelId);

    boolean existsByUserAndTravel(User user, Travel travel);

    Long countByTravel_TravelId(Long travelId);

    @Query("""
   select ut.role
   from UserTravel ut
   where ut.user.userId = :userId
     and ut.travel.travelId = :travelId
""")
String findRoleByUserAndTravel(@Param("userId") Long userId,
                              @Param("travelId") Long travelId);

    @Modifying
    @Transactional
    void deleteByUserAndTravel_TravelId(User user, Long travelId);
}
