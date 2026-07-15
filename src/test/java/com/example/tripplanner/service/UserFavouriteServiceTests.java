package com.example.tripplanner.service;

import com.example.tripplanner.dto.UserFavouriteDTO;
import com.example.tripplanner.entity.Destination;
import com.example.tripplanner.entity.Trip;
import com.example.tripplanner.entity.User;
import com.example.tripplanner.entity.UserFavourite;
import com.example.tripplanner.exception.DestinationNotFoundException;
import com.example.tripplanner.exception.FavouriteDestinationAlreadyExistsException;
import com.example.tripplanner.exception.TripNotFoundException;
import com.example.tripplanner.exception.UserNotFoundException;
import com.example.tripplanner.repository.DestinationRepository;
import com.example.tripplanner.repository.TripRepository;
import com.example.tripplanner.repository.UserFavouriteRepository;
import com.example.tripplanner.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserFavouriteServiceTests {
    @Mock
    private UserFavouriteRepository userFavouriteRepository;

    @Mock
    private TripRepository tripRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private DestinationRepository destinationRepository;

    @InjectMocks
    private UserFavouriteService userFavouriteService;

    @InjectMocks
    private UserService userService;

    @InjectMocks
    private DestinationService destinationService;

    private User createTestUser() {
        User user = new User();
        user.setId(1L);
        user.setEmail("ralitsa_sl@gmail.com");
        return user;
    }

    private Destination createTestDestination() {
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setCountry("Bulgaria");
        destination.setCity("Sofia");
        destination.setLatitude(42.0);
        destination.setLongitude(23.0);
        return destination;
    }

    private Trip createTestTrip() {
        Trip trip = new Trip();
        trip.setId(100L);
        trip.setName("Weekend Getaway");
        trip.setStartDate(LocalDate.now());
        trip.setEndDate(LocalDate.now().plusDays(3));
        trip.setCreatedDate(LocalDate.now());
        trip.setUser(createTestUser());
        trip.setTripDestinations(new ArrayList<>());
        return trip;
    }

    private UserFavourite createTestUserFavourite(User user, Trip trip) {
        UserFavourite favourite = new UserFavourite();
        favourite.setId(100L);
        favourite.setUser(user);
        favourite.setTrip(trip);
        return favourite;
    }

    @Test
    void addFavourite_invalidUserIdValidDestinationId_throwsUserNotFoundException() {
        Long userId = -1L;
        Long destinationId = 1L;

        when(userRepository.findById(userId)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> {userFavouriteService.addFavourite(userId, destinationId);});

        verify(userFavouriteRepository, times(0)).save(any(UserFavourite.class));
    }

    @Test
    void addFavourite_userNotFound_throwsTripNotFoundException() {
        Long userId = 1L;
        Long tripId = 10L;
        when(userRepository.findById(userId)).thenReturn(Optional.of(createTestUser()));
        when(tripRepository.findById(tripId)).thenReturn(Optional.empty());

        assertThrows(TripNotFoundException.class, () ->
                userFavouriteService.addFavourite(userId, tripId)
        );
        verify(userFavouriteRepository, times(0)).save(any());
    }

    @Test
    void addFavourite_alreadyExists_throwsFavouriteDestinationAlreadyExistsException() {
        Long userId = 1L;
        Long tripId = 3L;
        User user = createTestUser();
        Trip trip = createTestTrip();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(tripRepository.findById(tripId)).thenReturn(Optional.of(trip));
        when(userFavouriteRepository.existsByUserIdAndTripId(userId, tripId)).thenReturn(true);

        assertThrows(FavouriteDestinationAlreadyExistsException.class, () ->
                userFavouriteService.addFavourite(userId, tripId)
        );

        verify(userFavouriteRepository, never()).save(any());
    }

    @Test
    void addFavourite_newFavourite_savesAndReturnsDto() {
        Long userId = 1L;
        Long tripId = 100L;
        User user = createTestUser();
        Trip trip = createTestTrip();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        when(tripRepository.findById(tripId)).thenReturn(Optional.of(trip));

        when(userFavouriteRepository.existsByUserIdAndTripId(userId, tripId)).thenReturn(false);

        when(userFavouriteRepository.save(any(UserFavourite.class))).thenAnswer(invocation -> {
            UserFavourite saved = invocation.getArgument(0);
            saved.setId(999L);
            return saved;
        });

        UserFavouriteDTO result = userFavouriteService.addFavourite(userId, tripId);

        assertNotNull(result);
        assertEquals(999L, result.getId());
        assertEquals(userId, result.getUserId());
        assertEquals(tripId, result.getTrip().id());

        verify(userFavouriteRepository, times(1)).save(any(UserFavourite.class));
    }
}
