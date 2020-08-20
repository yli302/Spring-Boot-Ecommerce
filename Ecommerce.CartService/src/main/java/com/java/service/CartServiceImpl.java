package com.java.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.dao.CartRepository;
import com.java.entity.CartEntity;
import com.java.entity.CartEntity.PaymentStatus;

@Service
public class CartServiceImpl implements CartService{
	@Autowired
	CartRepository repo;
	
	@Override
	public long addCart(CartEntity cart) {
		return repo.save(cart).getId();
	}

	@Override
	public CartEntity getCartById(long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public void updateCart(CartEntity cart) {
		repo.save(cart);
	}

	@Override
	public void deleteCartById(CartEntity cart) {
		repo.delete(cart);
	}

	@Override
	public List<CartEntity> findByUsername(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public List<CartEntity> findByUsernameAndPaymentStatus(String username, PaymentStatus paymentStatus) {
		return repo.findByUsernameAndPaymentStatus(username, paymentStatus);
	}

	@Override
	public List<CartEntity> findByPaymentStatus(PaymentStatus paymentStatus) {
		return repo.findByPaymentStatus(paymentStatus);
	}

	@Override
	public List<CartEntity> getAll() {
		return repo.findAll();
	}
	

}
