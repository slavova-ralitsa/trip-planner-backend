package com.example.tripplanner.repository;

import com.example.tripplanner.entity.UserFavourite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFavouriteRepository extends JpaRepository<UserFavourite,Long> {

    boolean existsByUserIdAndDestinationId(Long userId, Long destinationId);

    void deleteByUserIdAndDestinationId(Long userId, Long destinationId);

    List<UserFavourite> findByUserId(Long id);
}
