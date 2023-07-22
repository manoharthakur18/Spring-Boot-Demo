package com.app.boot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.boot.entities.Product;
import com.app.boot.entities.UserInfo;
import com.app.boot.model.ProductModel;
import com.app.boot.securityConfig.AuthRequest;
import com.app.boot.service.JwtService;
import com.app.boot.service.ProductService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class MainController {

	@Autowired
	private ProductService productService;

	@Autowired
	private JwtService jwtService;

	@PostMapping("/adduser")
	public String addUser(@RequestBody UserInfo userInfo) {
		return productService.addUser(userInfo);
	}

	@PostMapping("/saveproduct")
	public ResponseEntity<Product> createProduct(@RequestBody ProductModel productModel) {
		return new ResponseEntity<Product>(productService.createProduct(productModel), HttpStatus.OK);
	}

	@GetMapping("/getproduct/{name}")
	public ResponseEntity<Product> getProduct(@PathVariable("name") String name) throws JsonProcessingException {
		ResponseEntity<Product> resProduct = new ResponseEntity<Product>(productService.getProduct(name),
				HttpStatus.OK);

//		ObjectMapper mapper = new ObjectMapper();
//        String result = mapper.writeValueAsString(resProduct.getBody());
//        System.out.println(result);

		return resProduct;
	}

	@GetMapping("/getproducts")
	public ResponseEntity<List<ProductModel>> getProducts() {
		return new ResponseEntity<List<ProductModel>>(productService.getProducts(), HttpStatus.OK);
	}

	@GetMapping("/getproductsname")
	public ResponseEntity<Map<String, Double>> getProductsForNamePrice() {
		List<ProductModel> products = productService.getProducts();

		Map<String, Double> response = new HashMap<>();
		for (ProductModel product : products) {
			response.put(product.getName(), product.getPrice());
		}

		return ResponseEntity.ok(response);
	}

	@PostMapping("/authenticate")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		return jwtService.generateToken(authRequest.getUserName());

	}

}
