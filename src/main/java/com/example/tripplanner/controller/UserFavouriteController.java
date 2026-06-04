package com.example.tripplanner.controller;

import com.example.tripplanner.dto.TripDTO;
import com.example.tripplanner.dto.UserFavouriteDTO;
import com.example.tripplanner.service.UserFavouriteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users/{userId}/favourites")
public class UserFavouriteController {

    private final UserFavouriteService userFavouriteService;

    public UserFavouriteController(UserFavouriteService userFavouriteService) {
        this.userFavouriteService = userFavouriteService;
    }

    @GetMapping
    public ResponseEntity<List<TripDTO>> getFavourites(@PathVariable Long userId) {
        List<TripDTO> favoriteDTOs = userFavouriteService.listFavourites(userId);
        return ResponseEntity.ok(favoriteDTOs);
    }

    @PostMapping("/{tripId}")
    public ResponseEntity<UserFavouriteDTO> addFavourite(@PathVariable Long userId, @PathVariable Long tripId) {
        // Вече връщаме DTO вместо Entity, за да избегнем LazyInitializationException
        UserFavouriteDTO savedFavourite = userFavouriteService.addFavourite(userId, tripId);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedFavourite);
    }

    @DeleteMapping("/{tripId}")
    public ResponseEntity<Void> removeFavourite(@PathVariable Long userId, @PathVariable Long tripId) {
        userFavouriteService.removeFavourite(userId, tripId);
        return ResponseEntity.noContent().build();
    }
}