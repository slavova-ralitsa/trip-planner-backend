package com.example.tripplanner.controller;

import com.example.tripplanner.dto.CreateTripRequestDTO;
import com.example.tripplanner.dto.TripDTO;
import com.example.tripplanner.entity.Destination;
import com.example.tripplanner.entity.Trip;
import com.example.tripplanner.entity.User;
import com.example.tripplanner.service.TripService;
import com.example.tripplanner.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;
    private final UserService userService;

    public TripController(TripService tripService, UserService userService) {
        this.tripService = tripService;
        this.userService = userService;
    }

    private Long resolveUserId(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByEmail(userDetails.getUsername());
        return user.getId();
    }

    @GetMapping
    public ResponseEntity<List<TripDTO>> getMyTrips(@AuthenticationPrincipal UserDetails userDetails) {
        Long userId = resolveUserId(userDetails);
        return ResponseEntity.ok(tripService.getUserTrips(userId));
    }

    @GetMapping("/{tripId}")
    public ResponseEntity<TripDTO> getTrip(@AuthenticationPrincipal UserDetails userDetails, @PathVariable("tripId") Long tripId) {
        Long userId = resolveUserId(userDetails);
        return ResponseEntity.ok(tripService.getTripByTripID(userId, tripId));
    }

    @PostMapping
    public ResponseEntity<TripDTO> createTrip(@AuthenticationPrincipal UserDetails userDetails, @RequestBody CreateTripRequestDTO request) {
        Long userId = resolveUserId(userDetails);
        TripDTO created = tripService.createTrip(
                userId,
                request.getName(),
                request.getStartDate(),
                request.getEndDate(),
                request.getDestinationIds()
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @Transactional
    @PutMapping("/{id}")
    public TripDTO updateTrip(@PathVariable Long id, @RequestBody Trip trip) {
        return tripService.updateTrip(id, trip);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrip(@AuthenticationPrincipal UserDetails userDetails, @PathVariable Long id) {
        Long userId = resolveUserId(userDetails);
        tripService.removeTrip(userId, id);
        return ResponseEntity.noContent().build();
    }

}

