package com.newsystem.server.rest;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.newsystem.server.domain.Comment;
import com.newsystem.server.domain.News;
import com.newsystem.server.service.CommentService;
import com.newsystem.server.service.NewsService;

 @RestController
@RequestMapping("/api/news")
public class AppRESTController {
	private final NewsService newsService;
	private final CommentService commentService;
	private final Map<String, Object> response = new LinkedHashMap<>();

	@Autowired
	public AppRESTController(NewsService newsService, CommentService commentService) {
		this.newsService = newsService;
		this.commentService = commentService;
	}

	@CrossOrigin(value = "*")
	@RequestMapping(method = RequestMethod.GET, value = "/getNews")
	@ResponseBody
	public List<News> findAll() {
		return newsService.getObj();
	}
	
    @CrossOrigin(value = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/getNewsById/{id}")
    public @ResponseBody News findById(@PathVariable String id){
        System.out.println("To jest id: " + id);
        return newsService.findById(id);
    }
    
    @CrossOrigin(value = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/getCommentsByNewsId/{id}")
    public @ResponseBody List<Comment> findByNewsId(@PathVariable String id){
        return commentService.findByNewsId(id);
    }
    
    @CrossOrigin(value = "*")
	@RequestMapping(method = RequestMethod.POST, value = "/getCommentsByNewsId/{id}")
	@ResponseBody
	public Map<String,Object> create(@Valid @RequestBody Comment commentEntity, BindingResult bindingResult) {
		
		if (checkError(bindingResult)) {
			commentService.create(commentEntity);
			response.put("message", "Comment created successfully");
			response.put("news", commentService.create(commentEntity));
		}
		return response;
	}




    @CrossOrigin(value = "*")
	@RequestMapping(method = RequestMethod.GET, value = "/getComments")
	@ResponseBody
	public List<Comment> findAllComment() {
		return commentService.getObj();
	}

	@CrossOrigin(value = "*")
	@RequestMapping(method = RequestMethod.POST, value = "/getNews")
	@ResponseBody
	public Map<String, Object> create(@Valid @RequestBody News newsEntity, BindingResult bindingResult) {
		if (checkError(bindingResult)) {
			newsService.create(newsEntity);
			response.put("message", "News created successfully");
			response.put("news", newsService.create(newsEntity));

		}
		return response;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/saveComment")
	@ResponseBody
	public Map<String,Object> createComment(@Valid @RequestBody Comment commentEntity, BindingResult bindingResult) {
		
		if (checkError(bindingResult)) {
			commentService.create(commentEntity);
			response.put("message", "Comment created successfully");
			response.put("news", commentService.create(commentEntity));
		}
		return response;
	}

	public boolean checkError(BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			List<FieldError> errors = bindingResult.getFieldErrors();
			response.put("message", "Error");
			for (FieldError error : errors) {
				response.put(error.getField(), error.getDefaultMessage());
			}
			return false;
		}

		else {
			return true;
		}

	}

}
