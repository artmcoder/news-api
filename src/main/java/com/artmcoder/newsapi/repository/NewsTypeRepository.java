package com.artmcoder.newsapi.repository;

import com.artmcoder.newsapi.model.NewsType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsTypeRepository extends JpaRepository<NewsType, Long> {
}
