package com.example.tripplanner.service;

import com.example.tripplanner.dto.TripDTO;
import com.example.tripplanner.entity.Destination;
import com.example.tripplanner.entity.Trip;
import com.example.tripplanner.entity.User;
import com.example.tripplanner.exception.DestinationNotFoundException;
import com.example.tripplanner.exception.TripNotFoundException;
import com.example.tripplanner.repository.DestinationRepository;
import com.example.tripplanner.repository.TripRepository;
import com.example.tripplanner.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TripServiceTests {

    @Mock
    private TripRepository tripRepository;

    @Mock
    private DestinationRepository destinationRepository;

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
    private Destination createTestDestination() {
        Destination destination = new Destination();
        destination.setId(2L);
        destination.setName("Sofia");
        destination.setLatitude(42.6977);
        destination.setLongitude(23.3219);
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

    @Test
    void calculateDistanceByHaversineFormula_validCoordinates() {
        Destination destination1 = new Destination();
        destination1.setLatitude(42.697);
        destination1.setLongitude(23.321);

        Destination destination2 = new Destination();
        destination2.setLatitude(42.145);
        destination2.setLongitude(24.751);

        double distance = TripService.calculateDistanceByHaversineFormula(destination1, destination2);

        assertEquals(131.0, distance, 5.0);
    }

    @Test
    void transformListOfIdsToListOfDestinations_success() {
        List<Long> ids = List.of(2L);
        when(destinationRepository.findById(2L)).thenReturn(Optional.of(createTestDestination()));

        List<Destination> result = tripService.transformListOfIdsToListOfDestinations(ids);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(2L, result.get(0).getId());
        verify(destinationRepository, times(1)).findById(2L);
    }

    @Test
    void transformListOfIdsToListOfDestinations_throwsDestinationNotFoundException() {
        List<Long> ids = List.of(2L);
        when(destinationRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(DestinationNotFoundException.class, () ->
                tripService.transformListOfIdsToListOfDestinations(ids)
        );
    }

    @Test
    void sortDestinations_emptyList() {
        List<Destination> emptyList = new ArrayList<>();

        List<Destination> result = tripService.sortDestinations(emptyList);

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void sortDestinations_singleDestination() {
        Destination destination = new Destination();
        destination.setId(1L);
        List<Destination> singleList = List.of(destination);

        List<Destination> result = tripService.sortDestinations(singleList);

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    void sortDestinations_MultipleDestinations_sortsByNearestNeighbor() {
        Destination start = new Destination();
        start.setId(1L);
        start.setLatitude(0.0);
        start.setLongitude(0.0);

        Destination far = new Destination();
        far.setId(2L);
        far.setLatitude(10.0);
        far.setLongitude(0.0);

        Destination closestToStart = new Destination();
        closestToStart.setId(3L);
        closestToStart.setLatitude(2.0);
        closestToStart.setLongitude(0.0);

        Destination mid = new Destination();
        mid.setId(4L);
        mid.setLatitude(5.0);
        mid.setLongitude(0.0);

        List<Destination> inputDestinations = List.of(start, far, closestToStart, mid);

        List<Destination> sortedResult = tripService.sortDestinations(inputDestinations);

        assertEquals(4, sortedResult.size());

        assertEquals(1L, sortedResult.get(0).getId());
        assertEquals(3L, sortedResult.get(1).getId());
        assertEquals(4L, sortedResult.get(2).getId());
        assertEquals(2L, sortedResult.get(3).getId());
    }

    @Test
    void createTrip_invalidInput_returnsSavedTrip() {
        Long userId = 1L;
        List<Long> destinationIds = List.of(10L);
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusDays(5);

        when(userRepository.findById(userId)).thenReturn(Optional.of(createTestUser()));
        when(destinationRepository.findById(10L)).thenReturn(Optional.of(createTestDestination()));
        when(tripRepository.save(any(Trip.class))).thenAnswer(invocation -> {
            Trip saved = invocation.getArgument(0);
            saved.setId(100L);
            return saved;
        });

        TripDTO result = tripService.createTrip(userId, "My Trip", start, end, destinationIds);

        assertNotNull(result);
        assertEquals("My Trip", result.name());
        verify(tripRepository, times(1)).save(any(Trip.class));
    }

    @Test
    void createTrip_invalidInput_throwsIllegalArgumentException() {
        Long userId = 1L;
        LocalDate start = LocalDate.now();
        LocalDate end = start.minusDays(1);

        when(userRepository.findById(userId)).thenReturn(Optional.of(createTestUser()));

        assertThrows(IllegalArgumentException.class, () ->
                tripService.createTrip(userId, "Invalid Trip", start, end, List.of())
        );
        verify(tripRepository, times(0)).save(any(Trip.class));
    }

    @Test
    void updateTrip_updatesNameOnly() {
        Long tripId = 100L;
        Trip existingTrip = createTestTrip();

        when(tripRepository.findById(tripId)).thenReturn(Optional.of(existingTrip));
        when(tripRepository.save(any(Trip.class))).thenReturn(existingTrip);

        TripDTO result = tripService.updateTrip(tripId, "New Name", null, null, null);

        assertEquals("New Name", existingTrip.getName());
        assertEquals("New Name", result.name());
        verify(tripRepository, times(1)).save(existingTrip);
    }

    @Test
    void getUserTrips_returnsList() {
        Long userId = 1L;
        Trip testTrip = createTestTrip();

        when(tripRepository.findByUserId(userId)).thenReturn(List.of(testTrip));

        List<TripDTO> result = tripService.getUserTrips(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(testTrip.getId(), result.get(0).id());
    }

    @Test
    void deleteTrip_validIds_deletesTrip() {
        Long userId = 1L;
        Long tripId = 100L;
        Trip testTrip = createTestTrip();

        when(tripRepository.findByIdAndUserId(tripId, userId)).thenReturn(Optional.of(testTrip));

        tripService.removeTrip(userId, tripId);

        verify(tripRepository, times(1)).delete(testTrip);
    }

    @Test
    void deleteTrip_invalidIds_throwsTripNotFoundException() {
        Long userId = 1L;
        Long tripId = 99L;
        when(tripRepository.findByIdAndUserId(tripId, userId)).thenReturn(Optional.empty());

        assertThrows(TripNotFoundException.class, () ->
                tripService.removeTrip(userId, tripId)
        );
        verify(tripRepository,times(0)).delete(any(Trip.class));
    }
}
