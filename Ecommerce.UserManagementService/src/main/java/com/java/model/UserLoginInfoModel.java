package com.java.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="Ecommerce_UserLoginInfo")
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginInfoModel {
	@Id
	String username;
	@Column(unique = true)
	String email;
	String password;
	@ElementCollection(fetch = FetchType.EAGER)
	@JoinTable(name = "Ecommerce_Authority", joinColumns = @JoinColumn(referencedColumnName = "username", name = "username"))
	List<String> authorities = new ArrayList<>();
}