package com.artmcoder.newsapi.services;

import com.artmcoder.newsapi.model.News;
import com.artmcoder.newsapi.model.NewsType;

import java.util.List;

public interface NewsService {
    List<News> getAll();

    News save(News news, NewsType newsType);

    void delete(Long id);

    News getById(Long newsId);
}
