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

import com.java.model.UserProfileModel;
import com.java.service.UserProfileService;

@RestController
@RequestMapping("/userProfile")
public class UserProfileController {
	@Autowired
	private UserProfileService service;
	
	@PostMapping
	public ResponseEntity<Object> addUser(@RequestBody UserProfileModel user, HttpServletRequest req) throws URISyntaxException {
		long id;
		try {
			id = service.addUserProfile(user);
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		return ResponseEntity.created(new URI(req.getRequestURI() + "/" + id)).build();
	}

	@PutMapping
	public ResponseEntity<Object> updateUser(@RequestBody UserProfileModel user) {
		service.updateUserProfile(user);
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{username}")
	public ResponseEntity<UserProfileModel> getUserLoginInfoByUsername(@PathVariable String username) {
		return ResponseEntity.ok(service.getUserProfileByUsername(username));
	}
}
