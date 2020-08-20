package com.java.service;

import java.util.List;

import com.java.entity.CartEntity;
import com.java.entity.CartEntity.PaymentStatus;

public interface CartService {
	public long addCart(CartEntity cart);
	
	public CartEntity getCartById(long id);
	
	public void updateCart(CartEntity cart);
	
	public void deleteCartById(CartEntity cart);
	
	public List<CartEntity> findByUsername(String username);

	public List<CartEntity> findByPaymentStatus(PaymentStatus paymentStatus);
	
	public List<CartEntity> findByUsernameAndPaymentStatus(String username, PaymentStatus paymentStatus);
	
	public List<CartEntity> getAll();
}
