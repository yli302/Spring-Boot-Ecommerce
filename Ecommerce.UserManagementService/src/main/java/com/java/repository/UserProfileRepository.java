package com.java.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.java.model.UserProfileModel;

public interface UserProfileRepository extends JpaRepository<UserProfileModel, Long>{
	public UserProfileModel findByUsername(String username);
	
	public void deleteByUsername(String username);
}
