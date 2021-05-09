package com.test.mykola.controller;

import com.test.mykola.exception.FileNotFoundException;
import com.test.mykola.exception.FileSaveException;
import com.test.mykola.exception.GoodsNotFoundException;
import com.test.mykola.exception.GoodsStatusNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.server.ServerErrorException;

import java.util.Map;

import static com.test.mykola.util.ResponseUtil.*;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = {GoodsNotFoundException.class, GoodsStatusNotFoundException.class, FileNotFoundException.class})
    public ResponseEntity<String> notFound(RuntimeException e) {
        return notFoundResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {FileSaveException.class, ServerErrorException.class})
    public ResponseEntity<String> serverError(RuntimeException e) {
        return serverErrorResponse(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException e) {
        return notValidExceptionResponse(e);
    }
}
