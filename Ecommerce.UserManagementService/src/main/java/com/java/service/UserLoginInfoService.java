package com.java.service;

import com.java.model.UserLoginInfoModel;

public interface UserLoginInfoService {
	public String addUserLoginInfo(UserLoginInfoModel userLoginInfoModel);
	
	public void deleteUserLoginInfoByUsername(String username);
	
	public void updateUserLoginInfo(UserLoginInfoModel userLoginInfoModel);
	
	public UserLoginInfoModel getUserLoginInfoByUsername(String username);
}
