package com.java.model;

import java.util.Date;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class CreditCardModel {
	private String holderName;
	private String creditCard; // store 12 digits, display last 4 digits..
	private Date expirationDate;
}