package com.app.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.boot.service.RestServices;

@RestController
@RequestMapping("/books")
public class YogiController {

	@Autowired
	private RestServices restService;

	@GetMapping("/getbooks")
	public ResponseEntity<String> getBooks() {
		return restService.getAllBooks();
	}
}
