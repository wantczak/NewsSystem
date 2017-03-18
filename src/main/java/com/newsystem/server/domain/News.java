package com.newsystem.server.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class News {
	@Id
	public String id;
	
	@NotNull (message="Pole nie moze byc puste")
	@Size(min=5, max=70, message="Minimum 5 znakow, max 70 znakow")
	public String title;
	
	@NotNull (message="Pole nie moze byc puste")
	@Size(min=5, max=70, message="Minimum 5 znakow")
	public String text;
	
	public String data;
	
	@NotNull
	@Size(min=1, message ="Minimum 1 znak")
	public String author;
	
	public News() {
	}

	public News(String title, String text, String data, String author) {
		super();
		this.title = title;
		this.text = text;
		this.data = data;
		this.author = author;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
	

}
