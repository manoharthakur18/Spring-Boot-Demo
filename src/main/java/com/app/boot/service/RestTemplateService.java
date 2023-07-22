package com.app.boot.service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.app.boot.entities.Product;
import com.app.boot.model.ProductModel;

@Service
public class RestTemplateService {

	RestTemplate restTemp = new RestTemplate();
	private static final String GET_ALL_PRODUCT_Url = "http://localhost:9090/getproducts";
	private static final String CREATE_PRODUCT_Url = "http://localhost:9090/saveproduct";
	private static final String GET_PRODUCT_URL = "http://localhost:9090/getproduct/{name}";

	public ResponseEntity<String> getAllProduct() {
		HttpHeaders header = new HttpHeaders();
		header.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>("parameters", header);
		ResponseEntity<String> response = restTemp.exchange(GET_ALL_PRODUCT_Url, HttpMethod.GET, entity, String.class);
		return response;
	}

	public ResponseEntity<Product> addProduct(ProductModel product) {
		return restTemp.postForEntity(CREATE_PRODUCT_Url, product, Product.class);
	}

	public Product getProductByName(String name) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("name", name);
		return restTemp.getForObject(GET_PRODUCT_URL, Product.class, param);
	}

}