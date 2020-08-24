package com.java.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.java.dto.AuthorityDto;
import com.java.dto.UserLoginInfo;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	UserLoginInfoFeign userLoginInfoFeign;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		ResponseEntity<UserLoginInfo> response = userLoginInfoFeign.getUserLoginInfoByUsername(username);
		
		if (response.hasBody()) {
			String password = response.getBody().getPassword();
			List<String> authorities = response.getBody().getAuthorities();
			return new User(username, password,	ConvertAuthorities(authorities));
		} else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
	}
	
	private List<AuthorityDto> ConvertAuthorities(List<String> auth) {
		List<AuthorityDto> list = new ArrayList<>();
		for (String str : auth) {
			list.add(new AuthorityDto(str));
		}
		return list;
	}

}