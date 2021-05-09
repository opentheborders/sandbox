package com.test.mykola.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.Collections;
import java.util.Map;

class ResponseUtilUTest {

    private static final String MESSAGE = "some_message";

    @Test
    void ok_generateNotFoundResponse() {
        ResponseEntity<String> actualResponse = ResponseUtil.notFoundResponse(MESSAGE);
        ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.NOT_FOUND).body(MESSAGE);

        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void ok_generateServerErrorResponse() {
        ResponseEntity<String> actualResponse = ResponseUtil.serverErrorResponse(MESSAGE);
        ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(MESSAGE);

        Assertions.assertEquals(expectedResponse, actualResponse);
    }

    @Test
    void ok_generateNotValidExceptionResponse() {
        MethodParameter methodParameter = Mockito.mock(MethodParameter.class);
        BindingResult bindingResult = Mockito.mock(BindingResult.class);
        FieldError fieldError = new FieldError("object", "field", "message");
        Mockito.when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(fieldError));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(methodParameter, bindingResult);

        ResponseEntity<Map<String, String>> actualResponse = ResponseUtil.notValidExceptionResponse(ex);
        ResponseEntity<Map<String, String>> expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Collections.singletonMap("field", "message"));

        Assertions.assertEquals(expectedResponse, actualResponse);
    }
}