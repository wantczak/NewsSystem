package com.newsystem.server;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.newsystem.server.domain.News;
import com.newsystem.server.repository.NewsRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsSystemApplicationTests {
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper(); 
	private static final RestTemplate restTemplate = new RestTemplate();
	
	@Autowired
	private NewsRepository newsRepository;
	
	@BeforeClass
	public static void setUpBefore() throws Exception{
		
	}
	
	@AfterClass
	public static void afterTest() throws Exception{
		
	}
	
	@Before
	public void setUp() throws Exception{
		
	}
	
	@After
	public void after() throws Exception{
		
	}
	@Test
	public void testCreateNews() throws IOException {
		Map<String,Object> requestBody = new HashMap<>();
		//requestBody.put("title", "TytulTestowy");
		//requestBody.put("text", "TrescTestowa");
		requestBody.put("data", "11.11.11");
		requestBody.put("author", "Wojcieszek");
		
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		HttpEntity<String> httpEntity = new HttpEntity<>(OBJECT_MAPPER.writeValueAsString(requestBody),requestHeaders);
		Map<String,Object> apiResponse = restTemplate.postForObject("http://localhost:8080/api/news/saveNews", httpEntity, Map.class,Collections.EMPTY_MAP);
		assertNotNull(apiResponse);
		
		//sprawdzanie poprawnosci danych 
		String message = apiResponse.get("message").toString();
		assertEquals("News created successfully",message);
		
		String newsId = ((Map<String,Object>)apiResponse.get("news")).get("id").toString();
		assertNotNull(newsId);
		
		News news = newsRepository.findOne(newsId);
		assertEquals("TytulTestowy",news.getTitle());
		assertEquals("TrescTestowa",news.getText());
		assertEquals("11.11.11",news.getData());
		assertEquals("Wojcieszek",news.getAuthor());

	}

}
