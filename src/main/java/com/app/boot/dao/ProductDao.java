package com.app.boot.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.boot.entities.Product;

public interface ProductDao extends JpaRepository<Product, Integer> {

	Optional<Product> findByName(String name);
	void deleteByName(String name);


}
