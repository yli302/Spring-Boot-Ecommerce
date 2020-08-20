package com.java.service;

import com.java.entity.ProductEntity;

public interface ProductService {
	public ProductEntity getProductById(long productId);
	
	public long addProduct(ProductEntity product);
	
	public void deleteProductById(long productId);
	
	public void updateProduct(ProductEntity product);
}
