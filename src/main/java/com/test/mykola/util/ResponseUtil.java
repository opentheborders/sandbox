package com.test.mykola.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.HashMap;
import java.util.Map;

/**
 * TResponse util for generate response entities.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUtil {

    /**
     * Generate not valid exception response entity.
     *
     * @param e the MethodArgumentNotValidException exception.
     * @return the response entity with status Bad Request.
     */
    public static ResponseEntity<Map<String, String>> notValidExceptionResponse(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return ResponseEntity.badRequest().body(errors);
    }

    /**
     * Generate not found response entity.
     *
     * @param message the message with cause of exception.
     * @return the response entity with status Not Found.
     */
    public static ResponseEntity<String> notFoundResponse(String message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    }

    /**
     * Generate server error response entity.
     *
     * @param message the message with cause of exception.
     * @return the response entity with status Internal Server Error.
     */
    public static ResponseEntity<String> serverErrorResponse(String message) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
    }
}
