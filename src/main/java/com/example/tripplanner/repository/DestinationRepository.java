package com.example.tripplanner.repository;
import com.example.tripplanner.entity.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination,Long> {

    Optional<Destination> findByLatitudeAndLongitude(double latitude, double longitude);

    List<Destination> findByCountry(String country);

    List<Destination> findByCity(String city);
}
