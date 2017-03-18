package com.newsystem.server.service;

import java.util.List;

import com.newsystem.server.domain.Comment;

public interface CustomInterfaceComment {
	List<Comment> findByNewsId(String id);
}
