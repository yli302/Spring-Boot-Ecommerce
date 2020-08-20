package com.java.service;

import com.java.entity.ItemEntity;

public interface ItemService {
	public ItemEntity getItemById(long id);
	
	public ItemEntity getOneItemByProductId(long productId);
	
	public long addItem(ItemEntity item);
	
	public void deleteItemById(long id);
	
	public int countByProductId(long productId);
}
