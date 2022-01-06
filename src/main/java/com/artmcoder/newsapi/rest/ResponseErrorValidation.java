package com.artmcoder.newsapi.rest;

import com.artmcoder.newsapi.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Component
public class ResponseErrorValidation {
    public ResponseEntity<Response> mapValidationService(BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errorMap = new HashMap<>();

            for (FieldError error : result.getFieldErrors()) {
                errorMap.put(error.getField(), error.getDefaultMessage());
            }
            return new ResponseEntity<>(
                    Response.builder()
                            .time(LocalDateTime.now())
                            .data(errorMap)
                            .message("Data are invalid")
                            .status(HttpStatus.BAD_REQUEST)
                            .statusCode(HttpStatus.BAD_REQUEST.value())
                            .build(), HttpStatus.BAD_REQUEST);
        }
        return null;
    }
}
