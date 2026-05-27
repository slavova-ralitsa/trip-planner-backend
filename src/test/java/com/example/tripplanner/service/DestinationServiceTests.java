package com.example.tripplanner.service;

import com.example.tripplanner.exception.DestinationAlreadyExistsException;
import com.example.tripplanner.exception.DestinationNotFoundException;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.tripplanner.entity.Destination;
import com.example.tripplanner.repository.DestinationRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DestinationServiceTests {
    @Mock
    private DestinationRepository destinationRepository;

    @InjectMocks
    private DestinationService destinationService;

    private Destination createFirstTestDestination() {
        Destination destination = new Destination();
        destination.setId(1L);
        destination.setCountry("Bulgaria");
        destination.setCity("Sofia");
        destination.setLatitude(42.0);
        destination.setLongitude(23.0);
        return destination;
    }

    private Destination createSecondTestDestination() {
        Destination destination = new Destination();
        destination.setId(2L);
        destination.setCountry("Bulgaria");
        destination.setCity("Plovdiv");
        destination.setLatitude(42.15);
        destination.setLongitude(24.75);
        return destination;
    }

    @Test
    void createDestination_validInput_returnsSavedDestination() {
        Destination destination = createFirstTestDestination();

        when(destinationRepository.save(any(Destination.class))).thenReturn(destination);

        Destination result = destinationService.createDestination(destination);

        assertNotNull(result);
        assertEquals(destination.getId(), result.getId());
        assertEquals("Bulgaria", result.getCountry());
        assertEquals(42.0, result.getLatitude());
        assertEquals(23.0, result.getLongitude());
        verify(destinationRepository, times(1)).save(destination);
    }

    @Test
    void createDestination_invalidId_throwsDestinationAlreadyExistsException() {
        Destination destination = createFirstTestDestination();

        when(destinationRepository.findByLatitudeAndLongitude(42.0, 23.0)).thenReturn(Optional.of(destination));

        assertThrows(DestinationAlreadyExistsException.class, () -> {destinationService.createDestination(destination);});
        verify(destinationRepository, times(0)).save(any(Destination.class));
    }

    @Test
    void getDestinationById_validId_returnsDestination() {
        Destination destination = createFirstTestDestination();

        when(destinationRepository.findById(1L)).thenReturn(Optional.of(destination));

        Destination destinationReturn = destinationService.getDestinationById(1L);

        assertNotNull(destinationReturn);
        assertEquals(1L, destinationReturn.getId());
        assertEquals("Bulgaria", destinationReturn.getCountry());
        assertEquals(42.0, destinationReturn.getLatitude());
        assertEquals(23.0, destinationReturn.getLongitude());
        verify(destinationRepository, times(1)).findById(1L);
    }

    @Test
    void getDestinationById_invalidId_throwsDestinationNotFoundException() {
        when(destinationRepository.findById(-1L)).thenReturn(Optional.empty());

        assertThrows(DestinationNotFoundException.class, () -> {destinationService.getDestinationById((long) -1L);});
        verify(destinationRepository, times(1)).findById(-1L);
    }

    @Test
    void getDestinationByCoordinates_validLatitudeAndLongitude_returnsDestination() {
        Destination destination = createFirstTestDestination();

        when(destinationRepository.findByLatitudeAndLongitude(42.0, 23.0)).thenReturn(Optional.of(destination));

        Destination destinationReturn = destinationService.getDestinationByCoordinates(42.0, 23.0);

        assertNotNull(destinationReturn);
        assertEquals(1L, destinationReturn.getId());
        assertEquals("Bulgaria", destinationReturn.getCountry());
        assertEquals(42.0, destinationReturn.getLatitude());
        assertEquals(23.0, destinationReturn.getLongitude());
        verify(destinationRepository, times(1)).findByLatitudeAndLongitude(42.0, 23.0);
    }

    @Test
    void getDestinationByCoordinates_invalidLatitudeAndLongitude_throwsDestinationNotFoundException() {
        when(destinationRepository.findByLatitudeAndLongitude(-1.0, -2.0)).thenReturn(Optional.empty());

        assertThrows(DestinationNotFoundException.class, () -> {destinationService.getDestinationByCoordinates( -1.0, -2.0);});
        verify(destinationRepository, times(1)).findByLatitudeAndLongitude(-1.0, -2.0);
    }

    @Test
    void getDestinationsByCountry_validCountry_returnsDestination() {
        Destination destination = createFirstTestDestination();

        when(destinationRepository.findByCountry("Bulgaria")).thenReturn(List.of(destination));

        List<Destination> destinationReturn = destinationService.getDestinationsByCountry("Bulgaria");

        assertNotNull(destinationReturn);
        assertEquals(1, destinationReturn.size());
        assertEquals(1L, destinationReturn.get(0).getId());
        assertEquals("Bulgaria", destinationReturn.get(0).getCountry());
        assertEquals(42.0, destinationReturn.get(0).getLatitude());
        assertEquals(23.0, destinationReturn.get(0).getLongitude());
        verify(destinationRepository, times(1)).findByCountry("Bulgaria");
    }

    @Test
    void getDestinationsByCity_validCity_returnsDestination() {
        Destination destination = createFirstTestDestination();

        when(destinationRepository.findByCity("Sofia")).thenReturn(List.of(destination));

        List<Destination> destinationReturn = destinationService.getDestinationsByCity("Sofia");

        assertNotNull(destinationReturn);
        assertEquals(1, destinationReturn.size());
        assertEquals(1L, destinationReturn.get(0).getId());
        assertEquals("Bulgaria", destinationReturn.get(0).getCountry());
        assertEquals(42.0, destinationReturn.get(0).getLatitude());
        assertEquals(23.0, destinationReturn.get(0).getLongitude());
        verify(destinationRepository, times(1)).findByCity("Sofia");
    }

    @Test
    void updateDestination_validId_updatesDestinationCorrectly() {
        long destinationId = 1L;

        Destination destination = createFirstTestDestination();

        Destination newDestination = createSecondTestDestination();

        when(destinationRepository.findById(destinationId)).thenReturn(Optional.of(destination));
        when(destinationRepository.save(any(Destination.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Destination updatedDestination = destinationService.updateDestination(destinationId, newDestination);

        assertNotNull(updatedDestination);
        assertEquals(destinationId, updatedDestination.getId());
        assertEquals("Plovdiv", updatedDestination.getCity());
        assertEquals("Bulgaria", updatedDestination.getCountry());
        assertEquals(42.15, updatedDestination.getLatitude());
        assertEquals(24.75, updatedDestination.getLongitude());
        verify(destinationRepository, times(1)).save(any(Destination.class));
    }

    @Test
    void updateDestination_invalidId_throwsDestinationNotFoundException() {
        long destinationId = 1L;

        Destination destination = createSecondTestDestination();

        when(destinationRepository.findById(destinationId)).thenReturn(Optional.empty());

        assertThrows(DestinationNotFoundException.class, () -> {destinationService.updateDestination(destinationId, destination);});
        verify(destinationRepository, times(1)).findById(destinationId);
        verify(destinationRepository, times(0)).save(any(Destination.class));
    }

    @Test
    void deleteDestinationById_validId_deletesDestination() {
        Destination destination = createSecondTestDestination();

        when(destinationRepository.findById(2L)).thenReturn(Optional.of(destination));
        destinationService.deleteDestinationById(2L);
        verify(destinationRepository, times(1)).deleteById(2L);

    }
    @Test
    void deleteDestinationById_invalidId_throwsDestinationNotFoundException() {
        when(destinationRepository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(DestinationNotFoundException.class, () -> {destinationService.deleteDestinationById(2L);});

        verify(destinationRepository).findById(2L);
        verify(destinationRepository, times(0)).deleteById(any());
    }
}
