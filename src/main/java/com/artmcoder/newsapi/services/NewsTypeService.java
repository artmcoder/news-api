package com.artmcoder.newsapi.services;

import com.artmcoder.newsapi.model.News;
import com.artmcoder.newsapi.model.NewsType;

import java.util.List;

public interface NewsTypeService {
    List<NewsType> getAll();

    NewsType getById(Long id);

    NewsType save(NewsType news);

    void delete(Long id);
}
