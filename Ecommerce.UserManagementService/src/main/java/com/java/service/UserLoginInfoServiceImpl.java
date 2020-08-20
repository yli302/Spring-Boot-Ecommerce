package com.java.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.java.model.UserLoginInfoModel;
import com.java.repository.UserLoginInfoRepository;

@Service
public class UserLoginInfoServiceImpl implements UserLoginInfoService{
	@Autowired
	UserLoginInfoRepository repo;
	
	@Override
	public String addUserLoginInfo(UserLoginInfoModel userLoginInfoModel) {
		return repo.save(userLoginInfoModel).getUsername();
	}

	@Override
	public void deleteUserLoginInfoByUsername(String username) {
		repo.deleteById(username);
	}

	@Override
	public void updateUserLoginInfo(UserLoginInfoModel userLoginInfoModel) {
		repo.save(userLoginInfoModel);
	}

	@Override
	public UserLoginInfoModel getUserLoginInfoByUsername(String username) {
		return repo.findById(username).orElse(null);
	}

}
