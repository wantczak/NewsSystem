package com.newsystem.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.newsystem.server.domain.News;
import com.newsystem.server.repository.NewsRepository;
import static java.util.stream.Collectors.toList;
@Service
public class NewsService implements ServiceInterface<News> {

	private NewsRepository newsRepository;

	@Autowired
	public NewsService(NewsRepository newsRepository) {
		this.newsRepository = newsRepository;
	}

	@Override
	public List<News> getObj() {
		List<News> newsList = newsRepository.findAllByOrderByDataDesc();
		return convertToDTOs(newsList);
	}
	
	private List<News> convertToDTOs(List<News> models){
		return models.stream().map(this::convertToDTO).collect(toList());
	}
	
	private News convertToDTO(News model){
		News dto = new News();
		dto.setId(model.getId());
		dto.setData(model.getData());
		dto.setText(model.getText());
		dto.setTitle(model.getTitle());
		dto.setAuthor(model.getAuthor());
		return dto;
	}

	@Override
	public News create(News obj) {
		return newsRepository.save(obj);
	}

	@Override
	public News findById(String id) {
		News news = newsRepository.findOne(id);
		return news;
	}

	@Override
	public News update(News obj) {
		// TODO Auto-generated method stub
		return null;
	}

}
