package com.java.model;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class AddressModel{
	private String street;
	private String city;
	private String state;
	private String zipcode;
}