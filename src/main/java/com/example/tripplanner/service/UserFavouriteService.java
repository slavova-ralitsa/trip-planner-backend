package com.example.tripplanner.service;

import com.example.tripplanner.entity.Destination;
import com.example.tripplanner.entity.User;
import com.example.tripplanner.entity.UserFavourite;
import com.example.tripplanner.exception.DestinationNotFoundException;
import com.example.tripplanner.exception.FavouriteDestinationAlreadyExistsException;
import com.example.tripplanner.exception.FavouriteDestinationNotFoundException;
import com.example.tripplanner.exception.UserNotFoundException;
import com.example.tripplanner.repository.DestinationRepository;
import com.example.tripplanner.repository.UserFavouriteRepository;
import com.example.tripplanner.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserFavouriteService {

    private final UserFavouriteRepository userFavouriteRepository;

    private final UserRepository userRepository;

    private final DestinationRepository destinationRepository;

    public UserFavouriteService(UserFavouriteRepository userFavouriteRepository, UserRepository userRepository, DestinationRepository destinationRepository) {
        this.userFavouriteRepository = userFavouriteRepository;
        this.userRepository = userRepository;
        this.destinationRepository = destinationRepository;
    }

    public UserFavourite addFavourite(Long userId, Long destinationId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        Destination destination = destinationRepository.findById(destinationId)
                .orElseThrow(() -> new DestinationNotFoundException(destinationId));

        if(userFavouriteRepository.existsByUserIdAndDestinationId(userId, destinationId)) {
            throw new FavouriteDestinationAlreadyExistsException (destinationId);
        }

        UserFavourite userFavourite = new UserFavourite();
        userFavourite.setUser(user);
        userFavourite.setDestination(destination);

        return userFavouriteRepository.save(userFavourite);
    }

    public void removeFavourite(Long userId, Long destinationId) {
        if(!userFavouriteRepository.existsByUserIdAndDestinationId(userId, destinationId))
            throw new FavouriteDestinationNotFoundException(destinationId, userId);

        userFavouriteRepository.deleteByUserIdAndDestinationId(userId, destinationId);
    }

    public List<Destination> listFavourites(Long userId) {
        if (!userRepository.existsById(userId))
            throw new UserNotFoundException(userId);

        List<Destination> destinations = new ArrayList<>();
        List<UserFavourite> userFavourites = userFavouriteRepository.findByUserId(userId);
        for(UserFavourite userFavourite : userFavourites)
            destinations.add(userFavourite.getDestination());
        return destinations;
    }


}
