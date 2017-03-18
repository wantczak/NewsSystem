package com.newsystem.server.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.newsystem.server.domain.Comment;

@Repository
public interface CommentRepository extends MongoRepository<Comment,String> {
	List<Comment> findByNewsIdOrderByDataDesc(String id);

}
