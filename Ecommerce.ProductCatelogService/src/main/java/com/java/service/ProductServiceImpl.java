package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.java.entity.ProductEntity;
import com.java.reposiotry.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository repo;
	
	@Override
	public List<ProductEntity> findByCatalogueName(String catalogueName) {
		return repo.findByCatalogueName(catalogueName);
	}

	@Override
	public List<ProductEntity> findByCatalogueNameAndCatagoryName(String catalogueName, String categoryName) {
		return repo.findByCatalogueNameAndCatagoryName(catalogueName, categoryName);
	}

	@Override
	public Page<ProductEntity> findByCatalogueNameAndCatagoryName(String catalogueName, String categoryName, int max,
			int offset) {
		return repo.findByCatalogueNameAndCatagoryName(catalogueName, categoryName, PageRequest.of(offset, max));
	}

	@Override
	public int countOfProduct(String catalogueName, String categoryName) {
		List<ProductEntity> products = repo.findByCatalogueNameAndCatagoryName(catalogueName, categoryName);
		return products.size();
	}

	@Override
	public ProductEntity findByIdAndCatalogueNameAndCatagoryName(long id, String catalogueName, String categoryName) {
		return repo.findByIdAndCatalogueNameAndCatagoryName(id, catalogueName, categoryName).orElse(null);
	}

}
