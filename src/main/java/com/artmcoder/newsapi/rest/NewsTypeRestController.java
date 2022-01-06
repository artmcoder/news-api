package com.artmcoder.newsapi.rest;

import com.artmcoder.newsapi.model.NewsType;
import com.artmcoder.newsapi.model.Response;
import com.artmcoder.newsapi.services.NewsTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/news-types")
@RequiredArgsConstructor
public class NewsTypeRestController {
    private final NewsTypeService newsTypeService;
    private final ResponseErrorValidation responseErrorValidation;

    @GetMapping
    public ResponseEntity<Response> getAll() {
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(newsTypeService.getAll())
                        .message("News types list is retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("{newsTypeId}")
    public ResponseEntity<Response> getById(@PathVariable("newsTypeId") Long newsTypeId) {
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(newsTypeService.getById(newsTypeId))
                        .message("News type with id " + newsTypeId + " is retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<Response> save(@Valid @RequestBody NewsType newsType, BindingResult bindingResult) {
        ResponseEntity<Response> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        NewsType creatingNewsType = newsTypeService.save(newsType);
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(creatingNewsType)
                        .message("News type with name " + creatingNewsType.getName() + " is created")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PutMapping
    public ResponseEntity<Response> update(@Valid @RequestBody NewsType newsType, BindingResult bindingResult) {
        ResponseEntity<Response> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        NewsType creatingNewsType = newsTypeService.save(newsType);
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(creatingNewsType)
                        .message("News type with id " + creatingNewsType.getId() + " is updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @DeleteMapping("{newsTypeId}")
    public ResponseEntity<Response> delete(@PathVariable("newsTypeId") Long newsTypeId) {
        newsTypeService.delete(newsTypeId);
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .message("News type with id " + newsTypeId + " was deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
