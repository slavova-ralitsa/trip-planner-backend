package com.example.tripplanner.dto;

import java.time.LocalDateTime;

public class ErrorResponseDTO {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private ErrorCode errorCode;
    private String message;
    private String path;

    public ErrorResponseDTO(int status,ErrorCode errorCode, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.errorCode = errorCode;
        this.error = error;
        this.message = message;
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }


    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }
}

