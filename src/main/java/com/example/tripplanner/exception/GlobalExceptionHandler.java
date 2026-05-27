package com.example.tripplanner.exception;

import com.example.tripplanner.dto.ErrorCode;
import com.example.tripplanner.dto.ErrorResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ResponseEntity<ErrorResponseDTO> buildError(HttpStatus status, ErrorCode errorCode, String message, HttpServletRequest request) {
        ErrorResponseDTO error = new ErrorResponseDTO(
                status.value(),
                errorCode,
                status.name(),
                message,
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(DestinationNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleDestinationNotFound(DestinationNotFoundException ex, HttpServletRequest request) {
        logger.warn("Destination not found: {}", ex.getMessage());
        return buildError(HttpStatus.NOT_FOUND, ex.getErrorCode(), ex.getMessage(), request);
    }

    @ExceptionHandler(DestinationAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleDestinationExists(DestinationAlreadyExistsException ex, HttpServletRequest request) {
        logger.warn("Destination already exists: {}", ex.getMessage());
        return buildError(HttpStatus.CONFLICT, ex.getErrorCode(), ex.getMessage(), request);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserExists(UserAlreadyExistsException ex,  HttpServletRequest request) {
        logger.warn("User already exists: {}", ex.getMessage());
        return buildError(HttpStatus.CONFLICT, ex.getErrorCode(), ex.getMessage(), request);
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleUserNotFound(UserNotFoundException ex, HttpServletRequest request) {
        logger.warn("User not found: {}", ex.getMessage());
        return buildError(HttpStatus.NOT_FOUND, ex.getErrorCode(), ex.getMessage(), request);
    }

    @ExceptionHandler(FavouriteDestinationAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDTO> handleFavouriteDestinationExists(FavouriteDestinationAlreadyExistsException ex, HttpServletRequest request) {
        logger.warn("Favourite destination already exists: {}", ex.getMessage());
        return buildError(HttpStatus.CONFLICT, ex.getErrorCode(), ex.getMessage(), request);
    }

    @ExceptionHandler(FavouriteDestinationNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleFavouriteDestinationNotFound(FavouriteDestinationNotFoundException ex, HttpServletRequest request) {
        logger.warn("Favourite destination not found: {}", ex.getMessage());
        return buildError(HttpStatus.NOT_FOUND, ex.getErrorCode(), ex.getMessage(), request);
    }

    @ExceptionHandler(TripNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleTripNotFound(TripNotFoundException ex, HttpServletRequest request) {
        logger.warn("Trip not found: {}", ex.getMessage());
        return buildError(HttpStatus.NOT_FOUND, ex.getErrorCode(), ex.getMessage(), request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleGenericException(Exception ex, HttpServletRequest request) {
        logger.error("Internal server error occurred", ex);
        return buildError(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.GENERIC_ERROR, ex.getMessage(), request);    }
}
