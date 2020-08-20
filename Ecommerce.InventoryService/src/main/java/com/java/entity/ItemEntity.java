package com.java.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "Ecommerce_Inventory")
public class ItemEntity {
	@Id
	@GeneratedValue
	private long itemId;
	private long productId;
}
