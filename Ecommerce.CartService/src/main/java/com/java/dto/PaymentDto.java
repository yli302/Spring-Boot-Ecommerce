package com.java.dto;

import com.java.entity.CartEntity.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentDto {
	private long transactionId; 
	private PaymentStatus paymentStatus;	
}
