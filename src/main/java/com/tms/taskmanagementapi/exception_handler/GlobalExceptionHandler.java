package com.tms.taskmanagementapi.exception_handler;

import com.tms.taskmanagementapi.dto.ErrorResponseDto;
import com.tms.taskmanagementapi.exception.AuthenticationFailureException;
import com.tms.taskmanagementapi.exception.InvalidTokenException;
import com.tms.taskmanagementapi.exception.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

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

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponseDto> handleInvalidTokenException(InvalidTokenException exception, WebRequest webRequest){

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(this.getErrorResponse(exception, webRequest, HttpStatus.UNAUTHORIZED));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleGlobalException(Exception exception, WebRequest webRequest){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(this.getErrorResponse(exception, webRequest, HttpStatus.INTERNAL_SERVER_ERROR));
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> validationErrors = new HashMap<>();
        List<ObjectError> validationErrorList = ex.getBindingResult().getAllErrors();

        validationErrorList.forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String validationMsg = error.getDefaultMessage();
            validationErrors.put(fieldName, validationMsg);
        });
        return new ResponseEntity<>(validationErrors, HttpStatus.BAD_REQUEST);
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
