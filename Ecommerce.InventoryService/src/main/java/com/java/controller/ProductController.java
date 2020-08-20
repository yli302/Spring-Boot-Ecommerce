package com.java.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.entity.ProductEntity;
import com.java.service.ProductService;

@RestController
@RequestMapping("/inventory/products")
public class ProductController {
	@Autowired
	ProductService service;

	@GetMapping("/{productId}")
	public ResponseEntity<ProductEntity> getProductById(@PathVariable long productId) {
		ProductEntity product = service.getProductById(productId);
		if (product != null)
			return ResponseEntity.ok(product);
		else 
			return ResponseEntity.noContent().build();
	}
	
	@PostMapping
	public ResponseEntity<ProductEntity> addProduct(@RequestBody ProductEntity product, HttpServletRequest req) throws URISyntaxException {
		long id = service.addProduct(product);
		return ResponseEntity.created(new URI(req.getRequestURI() + "/" + id)).build();
	}
	
	@PutMapping
	public ResponseEntity<ProductEntity> updateProduct(@RequestBody ProductEntity product) {
		service.updateProduct(product);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{productId}")
	public ResponseEntity<ProductEntity> deleteProductById(@PathVariable long productId) {
		service.deleteProductById(productId);
		return ResponseEntity.ok().build();
	}
}
