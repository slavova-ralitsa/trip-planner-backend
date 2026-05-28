package com.example.tripplanner.dto;

import java.time.LocalDate;
import java.util.List;

public class CreateTripDTO {
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private List<Long> destinationIds;

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }

    public LocalDate getEndDate() { return endDate; }
    public void setEndDate(LocalDate endDate) { this.endDate = endDate; }

    public List<Long> getDestinationIds() { return destinationIds; }
    public void setDestinationIds(List<Long> destinationIds) { this.destinationIds = destinationIds; }
}
