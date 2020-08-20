package com.java.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "Ecommerce_Cart")
public class CartEntity {
	@Id
	@GeneratedValue
	private long id;
	private String username;
	@ElementCollection
	private List<ProductEntity> products;
	@Column(unique = true)
	private long transactionId; 
	private PaymentStatus paymentStatus;
	
	public enum PaymentStatus {
		DECLINED, AUTHORIZED, PENDING
	}
}
