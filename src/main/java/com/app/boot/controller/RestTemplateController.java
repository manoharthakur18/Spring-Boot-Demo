package com.app.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.boot.entities.Product;
import com.app.boot.model.ProductModel;
import com.app.boot.service.RestTemplateService;

@RestController
@RequestMapping("/resttemplate")
public class RestTemplateController {

	@Autowired
	private RestTemplateService restTempService;

	@GetMapping("/getallproduct")
	public ResponseEntity<String> getProduct() {
		return restTempService.getAllProduct();
	}

	@PostMapping("/addproduct")
	public ResponseEntity<Product> addProduct(@RequestBody ProductModel product) {
		return restTempService.addProduct(product);
	}

	@GetMapping("/getproduct/{name}")
	public Product getProduct(@PathVariable String name) {
		return restTempService.getProductByName(name);
	}
}
