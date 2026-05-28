package com.example.tripplanner.service;

import com.example.tripplanner.dto.DestinationDTO;
import com.example.tripplanner.dto.TripDTO;
import com.example.tripplanner.dto.TripDestinationDTO;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public List<TripDTO> getUserTrips(Long userId) {
        List<Trip> trips = tripRepository.findByUserId(userId);

        return trips.stream()
                .map(this::convertToTripDTO)
                .collect(Collectors.toList());
    }

    private TripDTO convertToTripDTO(Trip trip) {
        List<TripDestinationDTO> destinationDTOs = trip.getTripDestinations().stream()
                .map(td -> new TripDestinationDTO(
                        td.getDayIndex(),
                        convertToDestinationDTO(td.getDestination()) // Извикваме новия помощен метод!
                ))
                .toList();

        return new TripDTO(
                trip.getId(),
                trip.getName(),
                trip.getStartDate(),
                trip.getEndDate(),
                destinationDTOs
        );
    }

    private DestinationDTO convertToDestinationDTO(Destination dest) {
        return new DestinationDTO(
                dest.getId(),
                dest.getName(),
                dest.getCity(),
                dest.getCountry(),
                dest.getLatitude(),
                dest.getLongitude(),
                dest.getDescription(),
                dest.getRating()
        );
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

    @Transactional
    public TripDTO createTrip(Long userId, String tripName, LocalDate startDate, LocalDate endDate, List<Long> destinationIds) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date!");
        }

        Trip trip = new Trip();
        trip.setUser(user);
        trip.setName(tripName);
        trip.setStartDate(startDate);
        trip.setEndDate(endDate);
        trip.setCreatedDate(LocalDate.now());

        List<Destination> destinations = sortDestinations(transformListOfIdsToListOfDestinations(destinationIds));
        List<TripDestination> tripDestinations = new ArrayList<>();

        for (int i = 0; i < destinations.size(); i++) {
            TripDestination tripDestination = new TripDestination();
            tripDestination.setTrip(trip);
            tripDestination.setDestination(destinations.get(i));
            tripDestination.setDayIndex(i + 1);

            tripDestinations.add(tripDestination);
        }

        trip.setTripDestinations(tripDestinations);

        Trip savedTrip = tripRepository.save(trip);

        return convertToTripDTO(savedTrip);
    }

    @Transactional
    public TripDTO updateTrip(Long id, Trip trip) {
        Trip existingTrip = tripRepository.findById(id)
                .orElseThrow(() -> new TripNotFoundException(id));
        if(trip.getName() != null && !trip.getName().isBlank()) {
            existingTrip.setName(trip.getName());
        }
        if(trip.getStartDate().isBefore(trip.getEndDate())) {
            existingTrip.setStartDate(trip.getStartDate());
            existingTrip.setEndDate(trip.getEndDate());
        }
        return convertToTripDTO(tripRepository.save(existingTrip));
    }

    @Transactional
    public TripDTO getTripByTripID(Long userId, Long tripId) {
        Trip trip = tripRepository.findByIdAndUserId(tripId, userId)
                .orElseThrow(() -> new TripNotFoundException(tripId));

        return convertToTripDTO(trip);
    }

    @Transactional
    public void removeTrip(Long userId, Long tripId) {
        Trip trip = tripRepository.findByIdAndUserId(tripId, userId)
                .orElseThrow(() -> new TripNotFoundException(tripId));

        tripRepository.delete(trip);
    }
}
