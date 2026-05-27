package com.example.tripplanner.exception;

import com.example.tripplanner.dto.ErrorCode;

public class FavouriteDestinationNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public FavouriteDestinationNotFoundException(Long destinationId, Long userId) {
        super("Destination with id " + destinationId + "not found for user " + userId + "!");
        this.errorCode = ErrorCode.FAVOURITE_DESTINATION_NOT_FOUND;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
