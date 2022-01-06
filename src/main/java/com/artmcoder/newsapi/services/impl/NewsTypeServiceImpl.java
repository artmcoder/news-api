package com.artmcoder.newsapi.services.impl;

import com.artmcoder.newsapi.exceptions.NoSuchNewsTypeException;
import com.artmcoder.newsapi.model.NewsType;
import com.artmcoder.newsapi.repository.NewsTypeRepository;
import com.artmcoder.newsapi.services.NewsTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NewsTypeServiceImpl implements NewsTypeService {
    private final NewsTypeRepository newsTypeRepository;

    @Override
    public List<NewsType> getAll() {
        log.info("Getting all News Types");
        return newsTypeRepository.findAll();
    }

    @Override
    public NewsType getById(Long newsTypeId) {
        log.info("Getting News with id {}", newsTypeId);
        return newsTypeRepository.findById(newsTypeId)
                .orElseThrow(() -> new NoSuchNewsTypeException("News Type with id " + newsTypeId + " is not found"));
    }

    @Override
    public NewsType save(NewsType newsType) {
        String newsTypeName = newsType.getName();
        if (newsType.getId() != null) log.info("Update News Type with name {}", newsTypeName);
        else log.info("Saving new News Type with name {}", newsTypeName);
        return newsTypeRepository.save(newsType);
    }

    @Override
    public void delete(Long newsTypeId) {
        log.info("Deleting News Type with id {}", newsTypeId);
        newsTypeRepository.deleteById(newsTypeId);
    }
}
