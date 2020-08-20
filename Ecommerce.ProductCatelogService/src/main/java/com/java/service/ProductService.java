package com.java.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.java.entity.ProductEntity;

public interface ProductService {
	public List<ProductEntity> findByCatalogueName(String catalogueName);
	
	public List<ProductEntity> findByCatalogueNameAndCatagoryName(String catalogueName, String categoryName);
	
	public Page<ProductEntity> findByCatalogueNameAndCatagoryName(String catalogueName, String categoryName, int max, int offset);
	
	public int countOfProduct(String catalogueName, String categoryName);
	
	public ProductEntity findByIdAndCatalogueNameAndCatagoryName(long id, String catalogueName, String categoryName);
}
