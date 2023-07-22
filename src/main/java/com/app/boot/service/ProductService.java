package com.app.boot.service;

import java.util.List;

import com.app.boot.entities.Product;
import com.app.boot.entities.UserInfo;
import com.app.boot.model.ProductModel;

public interface ProductService {

	public Product createProduct(ProductModel productModel);

	public void updateProduct(ProductModel productModel);

	public Product getProduct(String name);
	
	public List<ProductModel> getProducts();

	public void deleteProduct(String name);
	
	public String addUser(UserInfo userInfo);
}
