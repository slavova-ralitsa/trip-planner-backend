package com.example.tripplanner.service;

import com.example.tripplanner.entity.Destination;
import com.example.tripplanner.entity.User;
import com.example.tripplanner.entity.UserFavourite;
import com.example.tripplanner.exception.DestinationNotFoundException;
import com.example.tripplanner.exception.UserNotFoundException;
import com.example.tripplanner.repository.DestinationRepository;
import com.example.tripplanner.repository.UserFavouriteRepository;
import com.example.tripplanner.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserFavouriteServiceTests {
    @Mock
    private UserFavouriteRepository userFavouriteRepository;

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

    @Test
    void addFavourite_validUserIdValidDestinationId_addsFavourite() {
        Long userId = 1L;
        Long destinationId = 1L;

        User user = createTestUser();
        Destination destination = createTestDestination();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(destinationRepository.findById(destinationId)).thenReturn(Optional.of(destination));
        when(userFavouriteRepository.existsByUserIdAndDestinationId(userId, destinationId)).thenReturn(false);
        when(userFavouriteRepository.save(any(UserFavourite.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        UserFavourite result = userFavouriteService.addFavourite(userId, destinationId);

        assertNotNull(result);
        assertEquals(user, result.getUser());
        assertEquals(destination, result.getDestination());

        verify(userFavouriteRepository).save(any(UserFavourite.class));
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
    void addFavourite_validUserIdInvalidDestinationId_throwsDestinationNotFoundException() {
        Long userId = 1L;
        Long destinationId = -1L;

        User user = createTestUser();

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(destinationRepository.findById(destinationId)).thenReturn(Optional.empty());
        assertThrows(DestinationNotFoundException.class, () -> {userFavouriteService.addFavourite(userId, destinationId);});

        verify(userFavouriteRepository, times(0)).save(any(UserFavourite.class));
    }


}
