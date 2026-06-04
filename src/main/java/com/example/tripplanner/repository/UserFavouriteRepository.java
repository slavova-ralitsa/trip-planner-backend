package com.example.tripplanner.repository;

import com.example.tripplanner.entity.UserFavourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UserFavouriteRepository extends JpaRepository<UserFavourite, Long> {

    boolean existsByUserIdAndTripId(Long userId, Long tripId);

    Optional<UserFavourite> findByUserIdAndTripId(Long userId, Long tripId);

    void deleteByUserIdAndTripId(Long userId, Long tripId);

    List<UserFavourite> findByUserId(Long userId);
}