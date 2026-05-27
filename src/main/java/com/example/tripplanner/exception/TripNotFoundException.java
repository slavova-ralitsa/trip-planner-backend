package com.example.tripplanner.exception;

import com.example.tripplanner.dto.ErrorCode;

public class TripNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public TripNotFoundException(Long id) {
        super("Trip with id " + id + " not found!");
        this.errorCode = ErrorCode.TRIP_NOT_FOUND;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
