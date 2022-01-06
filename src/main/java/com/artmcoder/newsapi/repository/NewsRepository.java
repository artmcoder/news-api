package com.artmcoder.newsapi.repository;

import com.artmcoder.newsapi.model.News;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
}
