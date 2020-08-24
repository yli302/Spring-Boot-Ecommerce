package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.entity.ProductEntity;
import com.java.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository repo;
	
	@Override
	public ProductEntity getProductById(long productId) {
		return repo.findById(productId).orElse(null);
	}

	@Override
	public long addProduct(ProductEntity product) {
		return repo.save(product).getProductId();
	}

	@Override
	public void deleteProductById(long productId) {
		repo.deleteById(productId);
	}

	@Override
	public void updateProduct(ProductEntity product) {
		repo.save(product);
	}

}
