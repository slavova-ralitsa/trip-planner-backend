package com.example.tripplanner.exception;

import com.example.tripplanner.dto.ErrorCode;

public class UserAlreadyExistsException extends RuntimeException {

    private final ErrorCode errorCode;

    public UserAlreadyExistsException(Long id) {
        super("User with id " + id + " already exists!");
        this.errorCode = ErrorCode.USER_ALREADY_EXISTS;
    }

    public UserAlreadyExistsException() {
        super("User already exists!");
        this.errorCode = ErrorCode.USER_ALREADY_EXISTS;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}