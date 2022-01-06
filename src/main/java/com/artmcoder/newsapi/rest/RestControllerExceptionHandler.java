package com.artmcoder.newsapi.rest;

import com.artmcoder.newsapi.exceptions.NoSuchNewsException;
import com.artmcoder.newsapi.exceptions.NoSuchNewsTypeException;
import com.artmcoder.newsapi.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class RestControllerExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<Response> handleException(NoSuchNewsException noSuchNewsException) {
        return new ResponseEntity<>(
                Response.builder()
                        .time(LocalDateTime.now())
                        .message(noSuchNewsException.getMessage())
                        .status(HttpStatus.NOT_FOUND)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Response> handleException(NoSuchNewsTypeException noSuchNewsTypeException) {
        return new ResponseEntity<>(
                Response.builder()
                        .time(LocalDateTime.now())
                        .message(noSuchNewsTypeException.getMessage())
                        .status(HttpStatus.NOT_FOUND)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build(), HttpStatus.NOT_FOUND);
    }
}
