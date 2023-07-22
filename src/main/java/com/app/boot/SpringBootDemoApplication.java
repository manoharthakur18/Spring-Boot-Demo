package com.app.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.app.boot.entities.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootDemoApplication.class, args);

//		ObjectMapper objectMapper = new ObjectMapper();
//		Product product = new Product(1234, "Hp Laptop", 229999.0, "Ksolves");
//		try {
//			//serialization
//			String json = objectMapper.writeValueAsString(product);
//			System.out.println(json);
//			//Deserialization: JSON to Java object
//			Product deserializedObject = objectMapper.readValue(json, Product.class);
//			System.out.println(deserializedObject);
//		}catch(Exception e) {
//			System.out.println("Error");
//		}

	}
}
