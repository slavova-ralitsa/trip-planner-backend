package com.example.tripplanner.exception;

import com.example.tripplanner.dto.ErrorCode;

public class DestinationNotFoundException extends RuntimeException {

    private final ErrorCode errorCode;

    public DestinationNotFoundException(Long id) {
        super("Destination with id " + id + " not found!");
        this.errorCode = ErrorCode.DESTINATION_NOT_FOUND;
    }
    public DestinationNotFoundException(double latitude, double longitude) {
        super("Destination with latitude " + latitude + " and longitude " + longitude + " not found");
        this.errorCode = ErrorCode.DESTINATION_NOT_FOUND;

    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}