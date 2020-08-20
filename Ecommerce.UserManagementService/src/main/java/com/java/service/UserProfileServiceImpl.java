package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.model.UserProfileModel;
import com.java.repository.UserProfileRepository;

@Service
public class UserProfileServiceImpl implements UserProfileService{
	@Autowired
	UserProfileRepository repo;
	
	@Override
	public long addUserProfile(UserProfileModel userProfileModel) {
		return repo.save(userProfileModel).getId();
	}

	@Override
	public void deleteUserProfileById(long id) {
		repo.deleteById(id);
	}

	@Override
	public void updateUserProfile(UserProfileModel userProfileModel) {
		repo.save(userProfileModel);
	}

	@Override
	public UserProfileModel getUserProfileByUsername(String username) {
		return repo.findByUsername(username);
	}

	@Override
	public void deleteUserProfileByUsername(String username) {
		repo.deleteByUsername(username);
	}
	
}
