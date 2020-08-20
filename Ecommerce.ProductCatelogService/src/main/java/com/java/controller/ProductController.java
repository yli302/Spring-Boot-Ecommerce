package com.java.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.java.entity.ProductEntity;
import com.java.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	@Autowired
	ProductService service;
	
	@GetMapping(path = "/_all", params = "catalogueName")
	public ResponseEntity<List<ProductEntity>> findByCatalogueName(@RequestParam String catalogueName) {
		List<ProductEntity> products = service.findByCatalogueName(catalogueName);
		if (products !=null)
			return ResponseEntity.ok(products);
		else 
			return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/_all", params = "catalogueName, categoryName")
	public ResponseEntity<List<ProductEntity>> findByCatalogueNameAndCatagoryName(@RequestParam String catalogueName, @RequestParam String categoryName) {
		List<ProductEntity> products = service.findByCatalogueNameAndCatagoryName(catalogueName, categoryName);
		if (products !=null)
			return ResponseEntity.ok(products);
		else 
			return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/_page", params = "catalogueName, categoryName, max, offset")
	public ResponseEntity<List<ProductEntity>> findByCatalogueNameAndCatagoryName(@RequestParam String catalogueName, @RequestParam String categoryName, @RequestParam int max, @RequestParam int offset) {
		List<ProductEntity> products = service.findByCatalogueNameAndCatagoryName(catalogueName, categoryName, max, offset).toList();
		if (products !=null)
			return ResponseEntity.ok(products);
		else 
			return ResponseEntity.noContent().build();
	}
	
	@GetMapping(path = "/_count", params = "catalogueName, categoryName")
	public ResponseEntity<Integer> countOfProduct(@RequestParam String catalogueName, @RequestParam String categoryName) {
		int count = service.countOfProduct(catalogueName, categoryName);
		return ResponseEntity.ok(count);
	}
	
	@GetMapping
	public ResponseEntity<ProductEntity> findByIdAndCatalogueNameAndCatagoryName(long id, String catalogueName, String categoryName) {
		ProductEntity product = service.findByIdAndCatalogueNameAndCatagoryName(id, catalogueName, categoryName);
		if (product != null)
			return ResponseEntity.ok(product);
		else
			return ResponseEntity.noContent().build();
	}
}

