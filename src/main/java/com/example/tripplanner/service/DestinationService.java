package com.example.tripplanner.service;

import com.example.tripplanner.entity.Destination;
import com.example.tripplanner.exception.DestinationAlreadyExistsException;
import com.example.tripplanner.exception.DestinationNotFoundException;
import com.example.tripplanner.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public Destination createDestination(Destination destination) {
        Optional<Destination> existingDestination = destinationRepository.findByLatitudeAndLongitude(destination.getLatitude(), destination.getLongitude());
        if (existingDestination.isEmpty()) {
            return destinationRepository.save(destination);
        } else {
            throw new DestinationAlreadyExistsException(destination.getId());
        }
    }

    public Destination getDestinationById(Long id) {
        return destinationRepository.findById(id)
                .orElseThrow(() -> new DestinationNotFoundException(id));
    }

    public Destination getDestinationByCoordinates(double latitude, double longitude) {
        return destinationRepository.findByLatitudeAndLongitude(latitude, longitude)
                .orElseThrow(() -> new DestinationNotFoundException(latitude, longitude));
    }

    public List<Destination> getDestinationsByCountry(String country) {
        return destinationRepository.findByCountry(country);
    }

    public List<Destination> getDestinationsByCity(String city) {
        return destinationRepository.findByCity(city);
    }

    public void deleteDestinationById(Long id) {
        Optional<Destination> existingDestination = destinationRepository.findById(id);
        if (existingDestination.isEmpty())
            throw new DestinationNotFoundException((id));
        else
            destinationRepository.deleteById(id);
    }

    public Destination updateDestination(Long id, Destination destination) {
        Optional<Destination> existingDestination = destinationRepository.findById(id);
        if (existingDestination.isEmpty())
            throw new DestinationNotFoundException((id));
        else {
            destination.setId(id);
            return destinationRepository.save(destination);
        }
    }

    public List<Destination> findAll() {
        return destinationRepository.findAll();
    }
}
