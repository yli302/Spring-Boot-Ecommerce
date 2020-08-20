package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.java.dao.ItemRepository;
import com.java.entity.ItemEntity;

@Service
public class ItemServiceImpl implements ItemService{
	@Autowired
	ItemRepository repo;
	
	@Override
	public ItemEntity getItemById(long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public ItemEntity getOneItemByProductId(long productId) {
		return repo.findByproductId(productId, PageRequest.of(0, 1)).toList().get(0);
	}

	@Override
	public long addItem(ItemEntity item) {
		return repo.save(item).getItemId();
	}

	@Override
	public void deleteItemById(long id) {
		repo.deleteById(id);
	}

	@Override
	public int countByProductId(long productId) {
		//TODO
		return 0;
	}
}
