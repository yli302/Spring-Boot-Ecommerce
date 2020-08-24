package com.java.controller;

import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.java.dto.ChangePasswordDto;
import com.java.model.UserLoginInfoModel;
import com.java.service.UserLoginInfoService;

@RestController
@RequestMapping("/userLogin")
public class UserLoginController {
	@Autowired
	private UserLoginInfoService loginInfoService;

	@PostMapping
	public ResponseEntity<Object> addUser(@RequestBody UserLoginInfoModel user, HttpServletRequest req)
			throws URISyntaxException {
		String id;
		try {
			id = loginInfoService.addUserLoginInfo(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.created(new URI(req.getRequestURI() + "/" + id)).build();
	}

	@PutMapping
	public ResponseEntity<Object> updateUser(@RequestBody UserLoginInfoModel user) {
		System.out.println(user);
		loginInfoService.updateUserLoginInfo(user);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/changePassword")
	public ResponseEntity<Object> updatePassword(@RequestBody ChangePasswordDto passwordDto) {
		UserLoginInfoModel user = loginInfoService.getUserLoginInfoByUsername(passwordDto.getUsername());
		if (user != null && user.getPassword().equals(passwordDto.getOldPassword())) {
			user.setPassword(passwordDto.getNewPassword());
		}
		loginInfoService.updateUserLoginInfo(user);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{username}")
	public ResponseEntity<UserLoginInfoModel> getUserLoginInfoByUsername(@PathVariable String username) {
		UserLoginInfoModel user = loginInfoService.getUserLoginInfoByUsername(username);
		if (user != null)
			return ResponseEntity.ok(user);
		else
			return ResponseEntity.noContent().build();
	}
}
