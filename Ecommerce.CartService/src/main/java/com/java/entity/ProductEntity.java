package com.java.entity;

import lombok.Data;

@Data
public class ProductEntity {
	private long productId;
	private String productName;
	private int productQuantity;
	private int price;

	@Override
	public boolean equals(Object p) {
		ProductEntity product = (ProductEntity) p;
		return this.productId == product.productId;
	}
}
