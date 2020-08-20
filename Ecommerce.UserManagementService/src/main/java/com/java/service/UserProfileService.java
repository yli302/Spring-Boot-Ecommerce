package com.java.service;

import com.java.model.UserProfileModel;

public interface UserProfileService {
	public long addUserProfile(UserProfileModel userProfileModel);
	
	public void deleteUserProfileById(long id);
	
	public void deleteUserProfileByUsername(String username);
	
	public void updateUserProfile(UserProfileModel userProfileModel);
	
	public UserProfileModel getUserProfileByUsername(String username);
}
