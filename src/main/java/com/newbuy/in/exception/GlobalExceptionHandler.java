package com.newbuy.in.exception;

import com.newbuy.in.DTO.ApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.validation.ConstraintViolationException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {


    // Handle validation errors (@Valid)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
    	System.out.println("triggre");
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        return ResponseEntity.badRequest().body(
                new ApiResponse<>(false, errors, "Validation failed")
        );
    }

    // Handle JPA / Hibernate Constraint Violations (null or transient values)
    @ExceptionHandler({ ConstraintViolationException.class, DataIntegrityViolationException.class })
    public ResponseEntity<ApiResponse<Object>> handleJpaConstraintExceptions(Exception ex) {
    	System.out.println("triggre");
        return ResponseEntity.badRequest().body(
                new ApiResponse<>(false, null, "Database constraint violation: " + ex.getMessage())
        );
    }

    // Handle other generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleGeneralException(Exception ex) {
    	System.out.println("triggre");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new ApiResponse<>(false, null, "An unexpected error occurred: " + ex.getMessage())
        );
    }
}
