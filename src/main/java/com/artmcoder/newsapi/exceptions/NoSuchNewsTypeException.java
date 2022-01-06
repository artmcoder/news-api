package com.artmcoder.newsapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NoSuchNewsTypeException extends RuntimeException {
    public NoSuchNewsTypeException(String message) {
        super(message);
    }
}
