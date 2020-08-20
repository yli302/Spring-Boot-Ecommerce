package com.java.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long>{
	
}
