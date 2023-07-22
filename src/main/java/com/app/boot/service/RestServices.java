package com.app.boot.service;

import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestServices {
	
	RestTemplate restTemplate = new RestTemplate();

	private static final String GET_BOOKS_URL = "http://172.16.11.29:8080/getallbooks";

	public ResponseEntity<String> getAllBooks() {
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", header);
		ResponseEntity<String> response = restTemplate.exchange(GET_BOOKS_URL, HttpMethod.GET, entity, String.class);
		return response;
	}
}
