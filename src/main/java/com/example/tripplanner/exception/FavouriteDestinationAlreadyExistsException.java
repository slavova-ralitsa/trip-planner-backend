package com.example.tripplanner.exception;

import com.example.tripplanner.dto.ErrorCode;

public class FavouriteDestinationAlreadyExistsException extends RuntimeException {

    private final ErrorCode errorCode;

    public FavouriteDestinationAlreadyExistsException(Long id) {
        super("Favourite destination with id " + id + "already exists!");
        this.errorCode = ErrorCode.FAVOURITE_DESTINATION_ALREADY_EXISTS;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
