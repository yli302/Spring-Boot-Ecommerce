package com.java.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import com.java.entity.ItemEntity;

public interface ItemRepository extends JpaRepository<ItemEntity, Long>{
	public Page<ItemEntity> findByproductId(long productId, PageRequest of);
}
