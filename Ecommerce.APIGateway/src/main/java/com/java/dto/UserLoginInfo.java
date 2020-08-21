package com.java.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class UserLoginInfo {
	String username;
	String email;
	String password;
	List<String> authorities = new ArrayList<>();
}
