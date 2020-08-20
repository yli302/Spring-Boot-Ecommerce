package com.java.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name="Ecommerce_User")
public class UserProfileModel {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;
	@Column(unique = true)
	private String username;
	@Column(unique = true)
	private String email;
	@ElementCollection
	private List<AddressModel> addresses;
	@Embedded
	private AddressModel billingAddress;
	long phoneNumber;
	@ElementCollection
	private List<CreditCardModel> cards;
	private String portrait;
}
