package com.tms.taskmanagementapi.exception_handler;

import com.tms.taskmanagementapi.dto.ErrorResponseDto;
import com.tms.taskmanagementapi.exception.AuthenticationFailureException;
import com.tms.taskmanagementapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleResourceNotFoundException(ResourceNotFoundException exception, WebRequest webRequest){

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(this.getErrorResponse(exception, webRequest, HttpStatus.NOT_FOUND));
    }

    @ExceptionHandler(AuthenticationFailureException.class)
    public ResponseEntity<ErrorResponseDto> handleAuthenticationFailureException(AuthenticationFailureException exception, WebRequest webRequest){

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(this.getErrorResponse(exception, webRequest, HttpStatus.UNAUTHORIZED));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleException(Exception exception, WebRequest webRequest){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(this.getErrorResponse(exception, webRequest, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    private ErrorResponseDto getErrorResponse(Throwable exception, WebRequest webRequest, HttpStatus httpStatus){
       return new ErrorResponseDto(
                webRequest.getDescription(false),
                httpStatus,
                exception.getMessage(),
                LocalDateTime.now()
        );
    }

}
