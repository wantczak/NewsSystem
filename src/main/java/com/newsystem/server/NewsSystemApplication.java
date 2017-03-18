package com.newsystem.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.newsystem.server.domain.Comment;
import com.newsystem.server.domain.News;
import com.newsystem.server.service.CommentService;
import com.newsystem.server.service.NewsService;

@SpringBootApplication
@Configuration
@ComponentScan
public class NewsSystemApplication implements CommandLineRunner {
	
	@Autowired
	NewsService newsService;
	
	@Autowired
	CommentService commentService;

	
	public static void main(String[] args) {
		SpringApplication.run(NewsSystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}
}
