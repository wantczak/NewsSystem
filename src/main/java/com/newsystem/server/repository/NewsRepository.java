package com.newsystem.server.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.newsystem.server.domain.News;

@Repository
public interface NewsRepository extends MongoRepository<News,String> {
	List<News> findAllByOrderByDataDesc();
}
