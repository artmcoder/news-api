package com.artmcoder.newsapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

@Data
@SuperBuilder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {
    @JsonView(Views.List.class)
    protected LocalDateTime time;
    @JsonView(Views.List.class)
    protected int statusCode;
    @JsonView(Views.List.class)
    protected HttpStatus status;
    @JsonView(Views.List.class)
    protected String message;
    @JsonView(Views.List.class)
    protected Object data;
}
