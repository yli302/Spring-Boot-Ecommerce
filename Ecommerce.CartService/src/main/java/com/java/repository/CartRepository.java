package com.java.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.entity.CartEntity;
import com.java.entity.CartEntity.PaymentStatus;

public interface CartRepository extends JpaRepository<CartEntity, Long>{
	public List<CartEntity> findByUsername(String username);
	
	public List<CartEntity> findByPaymentStatus(PaymentStatus paymentStatus);
	
	public List<CartEntity> findByUsernameAndPaymentStatus(String username, PaymentStatus paymentStatus);
}
