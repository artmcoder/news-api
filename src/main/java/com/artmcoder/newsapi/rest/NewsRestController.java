package com.artmcoder.newsapi.rest;

import com.artmcoder.newsapi.model.News;
import com.artmcoder.newsapi.model.NewsType;
import com.artmcoder.newsapi.model.Response;
import com.artmcoder.newsapi.model.Views;
import com.artmcoder.newsapi.services.NewsService;
import com.artmcoder.newsapi.services.NewsTypeService;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequestMapping("api/v1/news")
@RequiredArgsConstructor
public class NewsRestController {
    private final NewsService newsService;
    private final NewsTypeService newsTypeService;
    private final ResponseErrorValidation responseErrorValidation;

    @GetMapping
    @JsonView(Views.List.class)
    public ResponseEntity<Response> getAll() {
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(newsService.getAll())
                        .message("News list is retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("{newsId}")
    @JsonView(Views.Full.class)
    public ResponseEntity<Response> getById(@PathVariable("newsId") Long newsId) {
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(newsService.getById(newsId))
                        .message("News with id " + newsId + " is retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @GetMapping("newsType/{newsTypeId}")
    @JsonView(Views.List.class)
    public ResponseEntity<Response> getNewsByNewsType(@PathVariable("newsTypeId") Long newsTypeId) {
        NewsType newsType = newsTypeService.getById(newsTypeId);
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(newsType.getNewsList())
                        .message("News list with news type " + newsType.getName() + " is retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PostMapping("{newsTypeId}")
    public ResponseEntity<Response> save(@Valid @RequestBody News news, BindingResult bindingResult,
                                         @PathVariable("newsTypeId") Long newsTypeId) {
        ResponseEntity<Response> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        NewsType newsType = newsTypeService.getById(newsTypeId);
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(newsService.save(news, newsType))
                        .message("News with name " + news.getName() + " is created")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @PutMapping("{newsTypeId}")
    public ResponseEntity<Response> update(@Valid @RequestBody News news, BindingResult bindingResult,
                                           @PathVariable("newsTypeId") Long newsTypeId) {
        ResponseEntity<Response> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        NewsType newsType = newsTypeService.getById(newsTypeId);
        News updatedNews = newsService.save(news, newsType);
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .data(updatedNews)
                        .message("News with id " + updatedNews.getId() + " is updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }

    @DeleteMapping("{newsId}")
    public ResponseEntity<Response> delete(@PathVariable("newsId") Long newsId) {
        News news = newsService.getById(newsId); // This line is not meaningless, because I am checking if the news exists to delete or not
        newsService.delete(news.getId());
        return ResponseEntity.ok(
                Response.builder()
                        .time(LocalDateTime.now())
                        .message("News with id " + newsId + " was deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );
    }
}
