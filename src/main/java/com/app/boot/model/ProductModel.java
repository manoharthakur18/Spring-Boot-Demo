package com.app.boot.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(value = Include.NON_NULL) 
public class ProductModel {
	@JsonProperty("productId")
	@JsonIgnore //-it will ignore this field in json
//	@JsonInclude(value = Include.NON_NULL) // ignore the value which is coming as null
	private Integer id;
	@JsonProperty("productName")
	private String name;
	@JsonProperty("productPrice")
//	@JsonInclude(value = Include.NON_NULL) 
	private Double price;
	@JsonProperty("productComName")
	private String comName;
	
	
	private String address;

}
