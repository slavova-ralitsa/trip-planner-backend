package com.example.tripplanner.service;

import com.example.tripplanner.entity.Destination;

import java.util.Comparator;

public class DistanceComparator implements Comparator<Destination> {

    private Destination current;

    public DistanceComparator(Destination current) {
        this.current = current;
    }

    @Override
    public int compare(Destination d1, Destination d2) {
        double distance1 = TripService.calculateDistanceByHaversineFormula(current, d1);
        double distance2 = TripService.calculateDistanceByHaversineFormula(current, d2);
        return Double.compare(distance1, distance2);
    }
}
