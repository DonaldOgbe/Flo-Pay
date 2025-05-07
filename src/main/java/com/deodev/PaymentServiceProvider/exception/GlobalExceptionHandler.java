package com.deodev.PaymentServiceProvider.exception;

import com.deodev.PaymentServiceProvider.response.GenericApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlesValidationError(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().
                getFieldErrors().
                stream().
                map(error -> error.getField() + ": " + error.getDefaultMessage()).
                collect(Collectors.toList());

        return ResponseEntity.
                badRequest().
                body(new GenericApiResponse<>(false, "Validation Failed", errors));
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserExist(UserAlreadyExistsException e) {
        return ResponseEntity.
                status(HttpStatus.BAD_REQUEST).
                body(new GenericApiResponse<>(false, e.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleGeneralException(Exception e) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GenericApiResponse<>(false, "An unexpected error occurred", e.getMessage()));
    }
}
