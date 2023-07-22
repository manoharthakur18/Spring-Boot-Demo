package com.app.boot.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.boot.dao.ProductDao;
import com.app.boot.dao.UserInfoRepository;
import com.app.boot.entities.Product;
import com.app.boot.entities.UserInfo;
import com.app.boot.exceptionHandler.UserNotFoundException;
import com.app.boot.model.ProductModel;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Service
//@Profile("dev")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private UserInfoRepository userRepository;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Value("${developer.name}")
	private String devName;

	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userRepository.save(userInfo);
		return "user added";
	}

	@PostConstruct
	public void printName() {
		System.out.println("******Bean created******* " + devName);
	}

	@PreDestroy
	public void destroyMethod() {
		System.out.println("*******************Bean is destroyed******************");
	}

	@Override
	public Product createProduct(ProductModel productModel) {
		// TODO Auto-generated method stub

		return productDao.save(convertModelToEntity(productModel));

	}

	@Override
	public List<ProductModel> getProducts() {
		// TODO Auto-generated method stub
		List<Product> product = productDao.findAll();
		List<ProductModel> productModel = new ArrayList<ProductModel>();
		for (Product p : product) {
			productModel.add(convertEntityToModel(p));
		}
		return productModel;
	}

	@Override
	public void updateProduct(ProductModel productModel) {
		// TODO Auto-generated method stub
		productDao.save(convertModelToEntity(productModel));
	}

	@Override
	public Product getProduct(String name) {
		// TODO Auto-generated method stub
		Optional<Product> product = productDao.findByName(name);
		if (product.isPresent()) {
			return product.get();
		} else {
			throw new UserNotFoundException("Product not found in db");
		}
	}

	@Override
	public void deleteProduct(String name) {
		// TODO Auto-generated method stub
		productDao.deleteByName(name);
	}

	public Product convertModelToEntity(ProductModel productModel) {

		Product product = new Product();
		product.setName(productModel.getName());
		product.setId(productModel.getId());
		product.setComName(productModel.getComName());
		product.setPrice(productModel.getPrice());

		return product;
	}

	public ProductModel convertEntityToModel(Product product) {

		ProductModel productModel = new ProductModel();
		productModel.setName(product.getName());
		productModel.setId(product.getId());
		productModel.setComName(product.getComName());
		productModel.setPrice(product.getPrice());

		return productModel;
	}

}
