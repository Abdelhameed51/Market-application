package com.onlinemarket.Repository;

import org.springframework.data.repository.CrudRepository;

import com.onlinemarket.Entities.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{
	
}
