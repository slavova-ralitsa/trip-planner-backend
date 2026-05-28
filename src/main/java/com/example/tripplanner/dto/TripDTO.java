package com.example.tripplanner.dto;

import java.time.LocalDate;
import java.util.List;

public record TripDTO(
        Long id,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        List<TripDestinationDTO> tripDestinations
) {}