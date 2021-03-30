package com.sachin.CricketGame.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.http.HttpStatus;

import java.util.Date;

public class ExceptionResponse {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date timestamp;
    private String message;
    private HttpStatus errorCode;
    private String details;

    public ExceptionResponse(Date timestamp, String message, String details, HttpStatus errorCode) {
        super();
        this.timestamp = timestamp;
        this.message = message;
        this.details = details;
        this.errorCode = errorCode;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }

    public String getDetails() {
        return details;
    }

    public HttpStatus getErrorCode() {
        return errorCode;
    }
}
