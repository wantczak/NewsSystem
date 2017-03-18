package com.newsystem.server.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newsystem.server.domain.Comment;
import com.newsystem.server.repository.CommentRepository;
import static java.util.stream.Collectors.toList;


@Service
public class CommentService implements ServiceInterface<Comment>, CustomInterfaceComment {

	public CommentRepository commentRepository;

	@Autowired
	public CommentService(CommentRepository commentRepository) {
		this.commentRepository = commentRepository;
	}

	@Override
	public List<Comment> getObj() {
		List<Comment> commentList = commentRepository.findAll();
		return convertToDTOs(commentList);
	}
	
	private List<Comment> convertToDTOs(List<Comment> models){
		return models.stream().map(this::convertToDTO).collect(toList());
	}
	
	private Comment convertToDTO(Comment comment){
		Comment dto = new Comment();
		dto.setId(comment.getId());
		dto.setData(comment.getData());
		dto.setAuthor(comment.getAuthor());
		dto.setNewsId(comment.getNewsId());
		dto.setComment(comment.getComment());
		return dto;
	}

	@Override
	public Comment create(Comment obj) {
		return commentRepository.save(obj);
	}

	@Override
	public Comment findById(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comment update(Comment obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
    public List<Comment> findByNewsId(String id) {
        List<Comment> commentList = commentRepository.findByNewsIdOrderByDataDesc(id);
        return convertToDTOs(commentList);
    }

}
