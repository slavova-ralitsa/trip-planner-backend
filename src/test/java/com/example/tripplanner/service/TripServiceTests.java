package com.example.tripplanner.service;

import com.example.tripplanner.entity.Destination;
import com.example.tripplanner.entity.Trip;
import com.example.tripplanner.entity.User;
import com.example.tripplanner.repository.DestinationRepository;
import com.example.tripplanner.repository.TripRepository;
import com.example.tripplanner.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripServiceTests {

    @Mock
    private TripRepository tripRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TripService tripService;

    private User createTestUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("ralitsa_sl@gmail.com");
        return user;
    }
    private Destination createDestination(Long id, String name) {
        Destination d = new Destination();
        d.setId(id);
        d.setName(name);
        return d;
    }

    private List<Destination> createTestDestinations() {
        return List.of(
                createDestination(1L, "Paris"),
                createDestination(2L, "Rome")
        );
    }

   @Test
    void getTripById_validId_returnsTrip() {

   }

}
