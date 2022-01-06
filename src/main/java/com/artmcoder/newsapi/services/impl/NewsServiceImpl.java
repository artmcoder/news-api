package com.artmcoder.newsapi.services.impl;

import com.artmcoder.newsapi.exceptions.NoSuchNewsException;
import com.artmcoder.newsapi.model.News;
import com.artmcoder.newsapi.model.NewsType;
import com.artmcoder.newsapi.repository.NewsRepository;
import com.artmcoder.newsapi.services.NewsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsServiceImpl implements NewsService {
    private final NewsRepository newsRepository;

    @Override
    public List<News> getAll() {
        log.info("Getting all News");
        return newsRepository.findAll();
    }

    @Override
    public News getById(Long newsId) {
        log.info("Getting News with id {}", newsId);
        return newsRepository.findById(newsId)
                .orElseThrow(() -> new NoSuchNewsException("News with id " + newsId + " is not found"));
    }

    @Override
    public News save(News news, NewsType newsType) {
        String newsName = news.getName();
        news.setNewsType(newsType);
        if (news.getId() != null) log.info("Update News with name {}", newsName);
        else log.info("Saving new News with name {}", newsName);
        return newsRepository.save(news);
    }

    @Override
    public void delete(Long newsId) {
        log.info("Deleting News with id {}", newsId);
        newsRepository.deleteById(newsId);
    }
}
