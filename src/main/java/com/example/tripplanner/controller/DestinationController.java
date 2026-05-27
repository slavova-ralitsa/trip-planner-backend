package com.example.tripplanner.controller;

import com.example.tripplanner.entity.Destination;
import com.example.tripplanner.service.DestinationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/destinations")

public class DestinationController {

    private final DestinationService destinationService;

    public DestinationController(DestinationService destinationService) {
        this.destinationService = destinationService;
    }

    @GetMapping("/{id}")
    public Destination getDestinationById(@PathVariable Long id) {
        return destinationService.getDestinationById(id);
    }

    @GetMapping("/country/{country}")
    List<Destination> getDestinationsByCountry(@PathVariable String country) {
        return destinationService.getDestinationsByCountry(country);
    }

    @GetMapping("/coordinates/{latitude}/{longitude}")
    public Destination getDestinationByCoordinates(@PathVariable double latitude, @PathVariable double longitude) {
        return destinationService.getDestinationByCoordinates(latitude, longitude);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Destination createDestination(@RequestBody Destination destination) {
        return destinationService.createDestination(destination);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        destinationService.deleteDestinationById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public Destination updateDestination(@PathVariable Long id, @RequestBody Destination destination) {
        return destinationService.updateDestination(id, destination);
    }

    @GetMapping("/search")
    public List<Destination> searchByCountry(@RequestParam String country) {
        return destinationService.getDestinationsByCountry(country);
    }

}
