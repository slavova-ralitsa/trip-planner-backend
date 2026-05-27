package com.example.tripplanner.exception;

import com.example.tripplanner.dto.ErrorCode;

public class DestinationAlreadyExistsException extends RuntimeException {

    private final ErrorCode errorCode;

    public DestinationAlreadyExistsException(Long id) {
        super("Destination with id " + id + "already exists!");
        this.errorCode = ErrorCode.DESTINATION_ALREADY_EXISTS;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}