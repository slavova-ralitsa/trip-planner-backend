package com.example.tripplanner.controller;

import com.example.tripplanner.entity.Destination;
import com.example.tripplanner.entity.UserFavourite;
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
    public List<Destination> getFavourites(@PathVariable Long userId) {
        return userFavouriteService.listFavourites(userId);
    }

    @PostMapping("/{destinationId}")
    @ResponseStatus(HttpStatus.CREATED)
    public UserFavourite addFavourite(@PathVariable Long userId, @PathVariable Long destinationId) {
        return userFavouriteService.addFavourite(userId, destinationId);
    }

    @DeleteMapping("/{destinationId}")
    public ResponseEntity<Void> removeFavourite(@PathVariable Long userId, @PathVariable Long destinationId) {
        userFavouriteService.removeFavourite(userId, destinationId);
        return ResponseEntity.noContent().build();
    }

}
