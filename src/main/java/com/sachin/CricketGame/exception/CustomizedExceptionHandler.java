package com.sachin.CricketGame.exception;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.util.Date;

@ControllerAdvice
@RestController
public class CustomizedExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(CustomException ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false),HttpStatus.INTERNAL_SERVER_ERROR);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(RecordNotFound.class)
    public final ResponseEntity<ExceptionResponse> ResourceNotFoundExceptions(RecordNotFound ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
                request.getDescription(false),HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        String errorMessage = "";
        BindingResult bindingResult = ex.getBindingResult();
        for(ObjectError error : bindingResult.getAllErrors())
            errorMessage += error.getDefaultMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), errorMessage,
                request.getDescription(false), HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

}
