package com.example.tripplanner.dto;

public record DestinationDTO(
        Long id,
        String name,
        String city,
        String country,
        Double latitude,
        Double longitude,
        String description,
        Double rating
) {}