package com.example.tripplanner.service;

import com.example.tripplanner.entity.Destination;
import com.example.tripplanner.entity.Trip;
import com.example.tripplanner.entity.TripDestination;
import com.example.tripplanner.entity.User;
import com.example.tripplanner.exception.DestinationNotFoundException;
import com.example.tripplanner.exception.TripNotFoundException;
import com.example.tripplanner.exception.UserNotFoundException;
import com.example.tripplanner.repository.DestinationRepository;
import com.example.tripplanner.repository.TripRepository;
import com.example.tripplanner.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TripService {

    private final TripRepository tripRepository;
    private final DestinationRepository destinationRepository;
    private final UserRepository userRepository;

    public TripService(TripRepository tripRepository, DestinationRepository destinationRepository, UserRepository userRepository) {
        this.tripRepository = tripRepository;
        this.destinationRepository = destinationRepository;
        this.userRepository = userRepository;
    }

    public static double calculateDistanceByHaversineFormula(Destination first, Destination second) {
        double latitude1 = Math.toRadians(first.getLatitude());
        double longitude1 = Math.toRadians(first.getLongitude());
        double latitude2 = Math.toRadians(second.getLatitude());
        double longitude2 = Math.toRadians(second.getLongitude());

        double dLatitude = latitude2 - latitude1;
        double dLongitude = longitude2 - longitude1;

        double a = Math.pow(Math.sin(dLatitude / 2), 2)
                + Math.cos(latitude1) * Math.cos(latitude2) * Math.pow(Math.sin(dLongitude / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));
        double earthRadius = 6371;

        return earthRadius * c;
    }

    protected List<Destination> sortDestinations(List<Destination> destinations) {
        if (destinations.isEmpty())
            return destinations;

        List<Destination> sortedDestinations = new ArrayList<>();
        List<Destination> remainingDestinations = new ArrayList<>(destinations);

        Destination currentDestination = remainingDestinations.get(0);
        sortedDestinations.add(currentDestination);
        remainingDestinations.remove(currentDestination);

        while (!remainingDestinations.isEmpty()) {
            Destination nearestDestination = remainingDestinations.get(0);
            for (int i = 1; i < remainingDestinations.size(); i++) {
                DistanceComparator comparator = new DistanceComparator(currentDestination);
                if (comparator.compare(remainingDestinations.get(i), nearestDestination) < 0) {
                    nearestDestination = remainingDestinations.get(i);
                }
        }
            sortedDestinations.add(nearestDestination);
            remainingDestinations.remove(nearestDestination);
            currentDestination = nearestDestination;
        }
        return sortedDestinations;
    }

    protected List<Destination> transformListOfIdsToListOfDestinations(List<Long> destinationIds) {
        List<Destination> destinations = new ArrayList<>();
        if(destinationIds.isEmpty())
            return destinations;

        for(long id : destinationIds) {
            Destination destination = destinationRepository.findById(id)
                            .orElseThrow(() -> new DestinationNotFoundException(id));
            destinations.add(destination);
        }
        return destinations;
    }

    public Trip createTrip(Long userId, String tripName, LocalDate startDate, LocalDate endDate, List<Long> destinationIds) {
        User user = userRepository.findById(userId)
                        .orElseThrow(() -> new UserNotFoundException(userId));
        if (endDate.isBefore(startDate)) {
            throw new RuntimeException("End date cannot be before start date!");
        }

        Trip trip = new Trip();
        trip.setUser(user);
        trip.setName(tripName);
        trip.setStartDate(startDate);
        trip.setEndDate(endDate);
        trip.setCreatedDate(LocalDate.now());

        List<Destination> destinations = sortDestinations(transformListOfIdsToListOfDestinations(destinationIds));
        List<TripDestination> tripDestinations = new ArrayList<>();
        for(int i = 0; i < destinations.size(); i++) {
            TripDestination tripDestination = new TripDestination();
            tripDestination.setTrip(trip);
            tripDestination.setDestination(destinations.get(i));
            tripDestination.setDayIndex(i + 1);

            tripDestinations.add(tripDestination);
        }

        trip.setTripDestinations(tripDestinations);
        return tripRepository.save(trip);
    }

    public List<Trip> getTripByUserId(Long userId) {
        return tripRepository.findByUserId(userId);
    }

    public Trip getTripByTripID(Long userId, Long tripId) {
        return tripRepository.findByIdAndUserId(userId, tripId)
                    .orElseThrow(() -> new TripNotFoundException(tripId));
    }

    public void removeTrip(Long userId, Long tripId) {
        Trip trip = tripRepository.findByIdAndUserId(userId, tripId)
                        .orElseThrow(() -> new TripNotFoundException(tripId));

        tripRepository.delete(trip);
    }
}
