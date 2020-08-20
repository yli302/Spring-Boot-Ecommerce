package com.java.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "Ecommerce_Product")
public class ProductEntity {
	@Id
	private long productId;
	private String productName;
	private int price;
}
