package com.java.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.java.dto.UserLoginInfo;


@FeignClient("USER-MANAGEMENT-SERVICE")
public interface UserLoginInfoFeign {
	@GetMapping("/userLogin/{username}")
	public ResponseEntity<UserLoginInfo> getUserLoginInfoByUsername(@PathVariable String username);
}
