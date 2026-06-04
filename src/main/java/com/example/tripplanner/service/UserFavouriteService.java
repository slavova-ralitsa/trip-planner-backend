package com.example.tripplanner.service;

import com.example.tripplanner.dto.TripDTO;
import com.example.tripplanner.dto.UserFavouriteDTO;
import com.example.tripplanner.entity.Trip;
import com.example.tripplanner.entity.User;
import com.example.tripplanner.entity.UserFavourite;
import com.example.tripplanner.exception.FavouriteDestinationAlreadyExistsException;
import com.example.tripplanner.exception.FavouriteDestinationNotFoundException;
import com.example.tripplanner.exception.TripNotFoundException;
import com.example.tripplanner.exception.UserNotFoundException;
import com.example.tripplanner.repository.TripRepository;
import com.example.tripplanner.repository.UserFavouriteRepository;
import com.example.tripplanner.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserFavouriteService {

    private final UserFavouriteRepository userFavouriteRepository;
    private final UserRepository userRepository;
    private final TripRepository tripRepository;

    public UserFavouriteService(UserFavouriteRepository userFavouriteRepository,
                                UserRepository userRepository,
                                TripRepository tripRepository) {
        this.userFavouriteRepository = userFavouriteRepository;
        this.userRepository = userRepository;
        this.tripRepository = tripRepository;
    }

    public UserFavouriteDTO addFavourite(Long userId, Long tripId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new TripNotFoundException(tripId));

        if (userFavouriteRepository.existsByUserIdAndTripId(userId, tripId)) {
            UserFavourite existing = userFavouriteRepository.findByUserIdAndTripId(userId, tripId)
                    .orElseThrow(() -> new FavouriteDestinationNotFoundException(tripId, userId));
            return toDTO(existing);
        }

        UserFavourite userFavourite = new UserFavourite();
        userFavourite.setUser(user);
        userFavourite.setTrip(trip);

        return toDTO(userFavouriteRepository.save(userFavourite));
    }

    private UserFavouriteDTO toDTO(UserFavourite userFavourite) {
        Trip trip = userFavourite.getTrip();

        TripDTO tripDTO = new TripDTO(
                trip.getId(),
                trip.getName(),
                trip.getStartDate(),
                trip.getEndDate(),
                List.of()
        );

        UserFavouriteDTO dto = new UserFavouriteDTO();
        dto.setId(userFavourite.getId());
        dto.setUserId(userFavourite.getUser().getId());
        dto.setTrip(tripDTO);
        return dto;
    }

    @Transactional
    public void removeFavourite(Long userId, Long tripId) {
        if (!userFavouriteRepository.existsByUserIdAndTripId(userId, tripId))
            throw new FavouriteDestinationNotFoundException(tripId, userId);

        userFavouriteRepository.deleteByUserIdAndTripId(userId, tripId);
    }

    public List<TripDTO> listFavourites(Long userId) {

        if (!userRepository.existsById(userId)) {
            throw new UserNotFoundException(userId);
        }

        List<UserFavourite> userFavourites =
                userFavouriteRepository.findByUserId(userId);

        return userFavourites.stream()
                .map(UserFavourite::getTrip)
                .map(trip -> new TripDTO(
                        trip.getId(),
                        trip.getName(),
                        trip.getStartDate(),
                        trip.getEndDate(),
                        List.of()
                ))
                .toList();
    }
}